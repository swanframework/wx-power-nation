package org.zongf.wx.power.nation;

import org.junit.Test;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;
import org.zongf.wx.power.nation.vo.ocr.TextArea;

/**
 * @author: zongf
 * @created: 2019-10-24
 * @since 1.0
 */
public class HttpClientUtilTest {


    @Test
    public void basicOcr() throws Exception{

        String imgPath = "/media/zongf/document/xxqg-imags/Screenshot_2019-10-26-21-17-29.png";

        OcrResponse basicOcrResponse = BaiduOcrUtil.doBasicOcr(imgPath);

        for (TextArea textArea : basicOcrResponse.getWords_result()) {
            System.out.println(textArea.getWords());
        }
    }

    @Test
    public void locationOcr() throws Exception{

        String imgPath = "/home/zongf/Desktop/imgs/1.jpg";

        OcrResponse basicOcrResponse = BaiduOcrUtil.doLocationOcr(imgPath);

        for (TextArea textArea : basicOcrResponse.getWords_result()) {
            System.out.println(textArea.getLocation() + " --> " + textArea.getWords());
        }
    }


}
