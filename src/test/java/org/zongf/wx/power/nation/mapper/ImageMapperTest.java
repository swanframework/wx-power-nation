package org.zongf.wx.power.nation.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.impl.ImageService;
import org.zongf.wx.power.nation.thread.OcrTask;

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

    @Test
    public void batchImportImages() throws Exception{

        String imageDir = "F:\\study-app\\20191027";

        OcrTask.doOcrTask(imageDir, ImageConstant.CATEGORY_QUESTION);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void test(){
        String str = "{\"words_result\":[{\"words\":\"中国人民银行是否可以向任何单位和个人提供\"},{\"words\":\"担保?\"},{\"words\":\"都不可提供\"},{\"words\":\"都可提供\"},{\"words\":\"可向单位提供,不可向个人提供\"},{\"words\":\"可向个人提供,不可向单位提供\"}],\"words_result_num\":7}";
        System.out.println(str.length());
    }


}
