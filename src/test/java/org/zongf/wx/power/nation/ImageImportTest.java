package org.zongf.wx.power.nation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.thread.BasicOcrTask;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageImportTest {

    @Test
    public void batchImportImages() throws Exception{

        // 图片目录
        String imageDir = "F:\\study-app\\xxqg-imags";

        // 开始批量导入
        BasicOcrTask.doOcrTask(imageDir, ImageConstant.CATEGORY_QUESTION);

        Thread.sleep(Integer.MAX_VALUE);
    }


}
