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

    // 批量导入图片，并做基础的ocr
    @Test
    public void batchImportImages() throws Exception{
        long start = System.currentTimeMillis();

        // 图片目录
        String imageDir = "G:\\study-app\\20191027";

        // 全部图片
        imageDir = "G:\\study-app\\question";

        // 开始批量导入
        BasicOcrTask.doOcrTask(imageDir, ImageConstant.CATEGORY_QUESTION);

        long end = System.currentTimeMillis();
        System.out.println("解析完成, 耗时:" + (end -start) + " ms");
    }

    // 精准ocr
    @Test
    public void batchAccurateOcr() throws Exception{
        this.imageService.batchAccurateOcr(ImageConstant.CATEGORY_QUESTION);
    }

    @Test
    public void test(){
        byte[] bytes = this.imageService.queryContent((long) 92);
        OcrResponse ocrResponse = BaiduOcrUtil.doBasicAccurateOcr(bytes);
    }

}
