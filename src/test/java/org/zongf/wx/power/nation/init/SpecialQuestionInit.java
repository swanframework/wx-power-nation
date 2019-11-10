package org.zongf.wx.power.nation.init;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.mapper.ImageSpecialMapper;
import org.zongf.wx.power.nation.mapper.SpecialQuestionMapper;
import org.zongf.wx.power.nation.po.ImageSpecialPO;
import org.zongf.wx.power.nation.po.SpecialItemPO;
import org.zongf.wx.power.nation.po.SpecialQuestionPO;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;
import org.zongf.wx.power.nation.vo.ocr.TextArea;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecialQuestionInit {


    @Autowired
    private ImageSpecialMapper imageSpecialMapper;

    @Autowired
    private SpecialQuestionMapper specialQuestionMapper;

    @Test
    public void testOne() throws Exception{
        parseSpecialFolder("G:\\study-app\\special\\2020.10.10_2020.12.20_测试");
    }


    @Test
    public void initSpecial() throws Exception{

        // 参数
        String folderPath = "G:\\study-app\\special";

        // 解析文件夹中目录
        List<String> fileList = Files.list(Paths.get(folderPath))
                .map(path -> path.toAbsolutePath().toString())
                .collect(Collectors.toList());

        // 解析专项答题
        for (String filePath : fileList) {
            parseSpecialFolder(filePath);
        }

    }

    // 解析专项答题文件夹
    public void parseSpecialFolder(String folderPath) throws Exception{

        // 获取文件路径
        List<String> fileList = Files.list(Paths.get(folderPath))
                .map(path -> path.toAbsolutePath().toString())
                .collect(Collectors.toList());

        // 解析对象
        SpecialQuestionPO specialQuestionPO = parseSpecialQuestionPO(folderPath);
        this.specialQuestionMapper.save(specialQuestionPO);

        // 解析图片
        List<SpecialItemPO> itemList = new ArrayList<>();
        for(int i=0; i<fileList.size(); i++) {

            byte[] fileBytes = FileUtils.getImageBytesWithoutHead(fileList.get(i), 350);

            // 调用百度ocr进行图片解析
            OcrResponse ocrResponse = BaiduOcrUtil.doBasicAccurateOcr(fileBytes);

            // 解析成图片对象
            ImageSpecialPO imageSpecialPO = converte(ocrResponse, i+1);
            imageSpecialPO.setContent(fileBytes);
            imageSpecialPO.setSpecialId(specialQuestionPO.getId());
            imageSpecialPO.setStatus("0");
            imageSpecialPO.setCreateTime(new Date());

            // 图片入库
            this.imageSpecialMapper.save(imageSpecialPO);
        }

    }

    private SpecialQuestionPO parseSpecialQuestionPO(String filePath) {

        // 统一路径符
        filePath = filePath.replaceAll("\\\\", "/");

        // 获取文件夹简单名称
        String simpleDirName = StringUtils.substringAfterLast(filePath, "/");

        // 文件夹分隔为数组
        String[] array = simpleDirName.split("_");

        // 创建对象
        return new SpecialQuestionPO(array[2], array[0], array[1]);
    }

    private ImageSpecialPO converte(OcrResponse ocrResponse, int seqNo) {

        List<TextArea> textAreaList = ocrResponse.getWords_result();

        // 获取题目类型
        String type = ocrResponse.getWords_result().get(0).getWords().substring(0, 3);

        // 获取开始下标
        int beginIdx = 0;
        for(int i=0; i<textAreaList.size(); i++) {
            if (textAreaList.get(i).getWords().endsWith("/10")) {
                beginIdx = i+1;
                break;
            }
        }

        // 开始遍历
        List<String> titleList = new ArrayList<>();
        List<String> optionList = new ArrayList<>();
        boolean isTitle = true;

        for(int i=beginIdx; i<textAreaList.size(); i++) {
            String line = textAreaList.get(i).getWords();

            // 说明是最后一行
            if (line.startsWith("查看提示") || line.startsWith("出题")) {
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
        ImageSpecialPO imageSpecialPO = new ImageSpecialPO();
        if (optionList.size() > 0) {
            imageSpecialPO.setOptions(JSONObject.toJSONString(optionList));
        }
        imageSpecialPO.setTitles((JSONObject.toJSONString(titleList)));
        imageSpecialPO.setSeqNo(seqNo);
        imageSpecialPO.setType(type);

        return imageSpecialPO;

    }

}
