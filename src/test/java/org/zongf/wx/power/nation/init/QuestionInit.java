package org.zongf.wx.power.nation.init;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.thread.BasicOcrTask;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;

import javax.sound.midi.Soundbank;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionInit {

    @Autowired
    private IImageService imageService;

    @Autowired
    private ImageMapper imageMapper;

    // 第1步: 图片做简单基本ocr
    @Test
    public void step1_basicOcr() throws Exception{
        long start = System.currentTimeMillis();

        // 图片目录
        String imageDir = "C:\\Users\\zong\\Documents\\雷电模拟器\\Pictures\\Screenshots";

        // 开始批量导入
        BasicOcrTask.doOcrTask(imageDir, ImageConstant.CATEGORY_QUESTION);

        long end = System.currentTimeMillis();
        System.out.println("解析完成, 耗时:" + (end -start) + " ms");
    }

    // 第2步: 对新导入图片做精准ocr
    @Test
    public void setp2_accurateOcr() throws Exception{
        this.imageService.batchAccurateOcr(ImageConstant.CATEGORY_QUESTION);
    }

}
