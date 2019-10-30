package org.zongf.wx.power.nation.factory;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.vo.ToParserImgInfo;
import org.zongf.wx.power.nation.vo.TodoImageInfoVO;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;
import org.zongf.wx.power.nation.vo.ocr.TextArea;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class ToParserImgInfoFactory {

    // 处理特殊符号
    public static String convertEnSign(String str) {
        // 英文符号转中文符号
        return str.replaceAll("\\(", " ( ")
                .replaceAll("\\)", " ) ")
                .replaceAll("\\?", "？")
                .replaceAll(",", "，")
                .replaceAll("!", "！");
    }

    private static boolean isTitleEndLine(String line){
        line = line.trim();
        if (line.endsWith("。") || line.endsWith("？") || line.endsWith("！")
                || line.endsWith("?") || line.endsWith("!")
                || line.endsWith(")") || line.endsWith("）")) {
           return true;
        }
        return false;
    }


    public static ToParserImgInfo create(ImagePO imagePO, int leftNum) {
        List<String> textLines = new ArrayList<>();

        // 反向序列化为对象.
        OcrResponse ocrResponse = JSONObject.parseObject(imagePO.getAccurateOcr(), OcrResponse.class);

        // 获取图片文本
        for (TextArea textArea : ocrResponse.getWords_result()) {
            String textLine = convertEnSign(textArea.getWords());
            textLines.add(textLine);
        }
        // 处理最后一行. 如果最后一行以出题:开头, 则移除
        if (textLines.get(textLines.size() - 1).startsWith("出题")) {
            textLines.remove(textLines.size() - 1);
        }

        // 计算标题行数
        int titleLines = 1;
        for (int i = 1; i < textLines.size(); i++) {
            String line = textLines.get(i).trim();
            if (line.endsWith("。") || line.endsWith("？") || line.endsWith("？") || line.endsWith("！")
                || line.endsWith(")")) {
                titleLines = i+1;
            }
        }

        // 如果答案行数大于6,那么说明可能解析有误, 设置答案选项为4
        if (textLines.size() - titleLines > 6) {
            titleLines = textLines.size() - 4;
        }

        // 构建返回对象
        ToParserImgInfo imgInfo = new ToParserImgInfo();
        imgInfo.setLeftNum(leftNum);
        imgInfo.setImgId(imagePO.getId());
        imgInfo.setTextLines(textLines);
        imgInfo.setTitleLines(titleLines);
        return imgInfo;


    }
}
