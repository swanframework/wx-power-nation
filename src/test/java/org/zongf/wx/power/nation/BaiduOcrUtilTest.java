package org.zongf.wx.power.nation;

import org.junit.Test;
import org.zongf.wx.power.nation.config.BaiduAccoutConfig;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;
import org.zongf.wx.power.nation.vo.ocr.TextArea;

/**
 * @author: zongf
 * @created: 2019-10-24
 * @since 1.0
 */
public class BaiduOcrUtilTest {

    // 测试不包含位置信息接口
    public void basicOcr() throws Exception{

        String imgPath = "F:/images/0.png";

        OcrResponse basicOcrResponse = BaiduOcrUtil.doBasicOcr(FileUtils.getFileBytes(imgPath));

        for (TextArea textArea : basicOcrResponse.getWords_result()) {
            System.out.println(textArea.getWords());
        }
    }

    // 测试包含位置信息接口
    public void locationOcr() throws Exception{

        String imgPath = "/home/zongf/Desktop/imgs/1.jpg";

        OcrResponse basicOcrResponse = BaiduOcrUtil.doLocationOcr(FileUtils.getFileBytes(imgPath));

        for (TextArea textArea : basicOcrResponse.getWords_result()) {
            System.out.println(textArea.getLocation() + " --> " + textArea.getWords());
        }
    }

}
