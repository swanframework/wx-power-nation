package org.zongf.wx.power.nation;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
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
import org.zongf.wx.power.nation.thread.LocOcrCallable;
import org.zongf.wx.power.nation.thread.LocOcrTask;
import org.zongf.wx.power.nation.vo.ImgLocOcrResult;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageImportTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private ImageMapper imageMapper;

    // 批量导入，并做基础的ocr
    @Test
    public void batchImportImages() throws Exception{
        long start = System.currentTimeMillis();

        // 图片目录
        String imageDir = "G:\\study-app\\20191027";

        // 全部图片
//        imageDir = "G:\\study-app\\xxqg-imags";

        // 开始批量导入
        BasicOcrTask.doOcrTask(imageDir, ImageConstant.CATEGORY_QUESTION);

        long end = System.currentTimeMillis();
        System.out.println("解析完成, 耗时:" + (end -start) + " ms");
    }

    // 精确ocr
    @Test
    public void doBatchLoc() throws Exception{
        LocOcrTask.doOcrTask(ImageConstant.CATEGORY_QUESTION);
    }

    @Test
    public void doLocOcrOnce() throws Exception {
        long id = 3;
        ImagePO imagePO = new ImagePO();
        imagePO.setId(id);
        ImgLocOcrResult ocrResult = new ImgLocOcrResult();
        new LocOcrCallable(null, null).doLocOcr(imagePO, ocrResult);
    }

}
