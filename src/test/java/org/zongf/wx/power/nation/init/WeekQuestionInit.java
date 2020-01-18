package org.zongf.wx.power.nation.init;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.mapper.ImageWeekMapper;
import org.zongf.wx.power.nation.mapper.WeekQuestionMapper;
import org.zongf.wx.power.nation.po.*;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;
import org.zongf.wx.power.nation.vo.ocr.TextArea;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WeekQuestionInit {

    /** 图片头部高度 */
    private final int IMAGE_HEAD_HEIGHT = 200;

    @Autowired
    private ImageWeekMapper imageWeekMapper;

    @Autowired
    private WeekQuestionMapper weekQuestionMapper;

    @Test
    public void testOne() throws Exception{
        String folderPath = "G:\\study-app\\week\\2020_01_2";
        parseSpecialFolder(folderPath, true);
    }


    @Test
    public void initSpecial() throws Exception{

        // 参数
        String folderPath = "G:\\study-app\\week";

        // 解析文件夹中目录
        List<String> fileList = Files.list(Paths.get(folderPath))
                .map(path -> path.toAbsolutePath().toString())
                .collect(Collectors.toList());

        // 解析专项答题
        for (String filePath : fileList) {
            parseSpecialFolder(filePath, true);
        }

    }

    // 解析专项答题文件夹
    public void parseSpecialFolder(String folderPath, boolean saveToDb) throws Exception{

        // 获取文件路径
        List<String> fileList = Files.list(Paths.get(folderPath))
                .map(path -> path.toAbsolutePath().toString())
                .collect(Collectors.toList());

        // 解析对象
        WeekQuestionPO weekQuestionPO = parseWeekQuestionPO(folderPath);

        if (saveToDb) {
            this.weekQuestionMapper.save(weekQuestionPO);
        }

        // 解析图片
        List<SpecialItemPO> itemList = new ArrayList<>();
        for(int i=0; i<fileList.size(); i++) {

            byte[] fileBytes = FileUtils.getImageBytesWithoutHead(fileList.get(i), IMAGE_HEAD_HEIGHT);

            // 调用百度ocr进行图片解析
            OcrResponse ocrResponse = BaiduOcrUtil.doBasicAccurateOcr(fileBytes);

            // 解析成图片对象
            ImageWeekPO imageWeekPO = converte(ocrResponse, i+1);
            imageWeekPO.setContent(fileBytes);
            imageWeekPO.setWeekId(weekQuestionPO.getId());
            imageWeekPO.setStatus("0");
            imageWeekPO.setCreateTime(new Date());

            // 图片入库
            if (saveToDb) {
                this.imageWeekMapper.save(imageWeekPO);
            }else {
                print(imageWeekPO);
            }
        }
    }

    private void print(ImageWeekPO imageSpecialPO) {

        System.out.println("\n****************************************\n");

        System.out.println("题目类型:" + imageSpecialPO.getType() + ", 题号:" + imageSpecialPO.getSeqNo());
        System.out.println("题目:" + imageSpecialPO.getTitles());
        System.out.println("选项:" + imageSpecialPO.getOptions());

    }

    private WeekQuestionPO parseWeekQuestionPO(String filePath) {

        // 统一路径符
        filePath = filePath.replaceAll("\\\\", "/");

        // 获取文件夹简单名称
        String simpleDirName = StringUtils.substringAfterLast(filePath, "/");

        // 文件夹分隔为数组
        String[] array = simpleDirName.split("_");

        // 创建对象
        return new WeekQuestionPO(array[0], array[1], Integer.valueOf(array[2]));
    }

    private ImageWeekPO converte(OcrResponse ocrResponse, int seqNo) {

        List<TextArea> textAreaList = ocrResponse.getWords_result();

        // 获取题目类型
        String type = ocrResponse.getWords_result().get(0).getWords().substring(0, 3);

        // 获取开始下标
        int beginIdx = 0;
        if (textAreaList.get(1).getWords().length() <= 2) {
            beginIdx = 2;
        }
        if (textAreaList.get(2).getWords().length() <= 2) {
            beginIdx = 3;
        }

        // 开始遍历
        List<String> titleList = new ArrayList<>();
        List<String> optionList = new ArrayList<>();
        boolean isTitle = true;

        for(int i=beginIdx; i<textAreaList.size(); i++) {
            String line = textAreaList.get(i).getWords();

            // 说明是最后一行
            if (line.startsWith("查看提示") || line.startsWith("出题") || line.startsWith("推荐")) {
                break;
            }

            // 如果行以大写字母开头, 说明是选项
            if(line.startsWith("A.") || line.startsWith("B.") || line.startsWith("C.") || line.startsWith("D.")
                    || line.startsWith("E.") || line.startsWith("F.") || line.startsWith("G.")|| line.startsWith("H.")){
                optionList.add(line);
                isTitle = false;
            }else if(optionList.size() > 0) {
                String option = optionList.get(optionList.size() - 1);
                option += line;
                optionList.set(optionList.size() - 1, option);
            }

            // 如果当前行是标题行, 则添加到标题中
            if(isTitle) titleList.add(line);
        }

        // 创建ImageSpecialPO 对象
        ImageWeekPO imageWeekPO = new ImageWeekPO();
        if (optionList.size() > 0) {
            imageWeekPO.setOptions(JSONObject.toJSONString(optionList));
        }
        imageWeekPO.setTitles((JSONObject.toJSONString(titleList)));
        imageWeekPO.setSeqNo(seqNo);
        imageWeekPO.setType(type);

        return imageWeekPO;

    }

}
