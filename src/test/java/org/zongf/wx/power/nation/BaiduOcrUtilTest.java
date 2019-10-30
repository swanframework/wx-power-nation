package org.zongf.wx.power.nation;

import org.junit.Test;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;
import org.zongf.wx.power.nation.vo.ocr.TextArea;

/** 百度ocr 工具类测试
 * @author: zongf
 * @created: 2019-10-24
 * @since 1.0
 */
public class BaiduOcrUtilTest {

    private String imgPath = "F:\\study-app\\123.png";


    // 测试通用文字基础解析, 自动去除头部
    @Test
    public void doBasicOcr() throws Exception{

        OcrResponse basicOcrResponse = BaiduOcrUtil.doBasicOcr(FileUtils.getImageBytesWithoutHead(imgPath));

        System.out.println("\n\n***********************************\n\n");
        for (TextArea textArea : basicOcrResponse.getWords_result()) {
            System.out.println(textArea.getWords());
        }
    }

    // 测试通用文字高精度解析, 自动去除头部
    @Test
    public void doBasicAccurateOcr() throws Exception{

        OcrResponse basicOcrResponse = BaiduOcrUtil.doBasicAccurateOcr(FileUtils.getImageBytesWithoutHead(imgPath));

        System.out.println("\n\n***********************************\n\n");
        for (TextArea textArea : basicOcrResponse.getWords_result()) {
            System.out.println(textArea.getLocation() + " --> " + textArea.getWords());
        }
    }

    // 测试通用文字基础解析, 不去除头部
    @Test
    public void doBasicOcrWithHead() throws Exception{

        OcrResponse basicOcrResponse = BaiduOcrUtil.doBasicOcr(FileUtils.getFileBytes(imgPath));

        System.out.println("\n\n***********************************\n\n");
        for (TextArea textArea : basicOcrResponse.getWords_result()) {
            System.out.println(textArea.getWords());
        }
    }

}
