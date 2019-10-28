package org.zongf.wx.power.nation.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.BaiduConstant;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.impl.ImageService;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.FileUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageMapperTest {

    @Autowired
    private ImageService imageService;


    @Test
    public void save() throws Exception {

        String imagePath = "/media/zongf/document/xxqg-imags/20191027/Screenshot_2019-10-27-13-29-41.png";

        byte[] bytes = Files.readAllBytes(Paths.get(imagePath));

        ImagePO imagePO = new ImagePO();
        imagePO.setName("测试图片");
        imagePO.setContent(bytes);
        imagePO.setCreateTime(new Date());
        imagePO.setType("1");
        imagePO.setOcr("ocr");

        System.out.println(imagePO.getContent().length);
//        this.imageService.save(imagePO);
    }

    @Test
    public void batchSave() {
        String imagePath = "/workspace/zongf/wx-power-nation/src/main/resources/images";
        this.imageService.batchSave(BaiduConstant.AK, BaiduConstant.SK, imagePath,"1");

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
