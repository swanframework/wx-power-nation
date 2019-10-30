package org.zongf.wx.power.nation;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OcrTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private ImageMapper imageMapper;

    // 批量导入，并做基础的ocr
    @Test
    public void batchImportImages() throws Exception{

        ImagePO imagePO = this.imageMapper.queryContent(1l);

        OcrResponse ocrResponse = BaiduOcrUtil.doBasicAccurateOcr(imagePO.getContent());

        String basicOcr = JSONObject.toJSONStringWithDateFormat(ocrResponse, "yyyy.MM.dd", SerializerFeature.PrettyFormat);

        System.out.println(basicOcr);

    }



}
