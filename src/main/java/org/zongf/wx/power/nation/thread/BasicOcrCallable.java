package org.zongf.wx.power.nation.thread;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.service.impl.ImageService;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.util.ImageUtil;
import org.zongf.wx.power.nation.util.SpringBeanUtil;
import org.zongf.wx.power.nation.vo.ImgBasicOcrResult;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class BasicOcrCallable implements Callable<ImgBasicOcrResult> {

    private Logger log = LoggerFactory.getLogger(BasicOcrCallable.class);

    private ConcurrentLinkedQueue<String> imageFilePathQueue;

    private IImageService imageService;

    private String category;

    public BasicOcrCallable(ConcurrentLinkedQueue<String> imageQueue, String category) {
        this.imageFilePathQueue = imageQueue;
        this.category = category;
        this.imageService = SpringBeanUtil.getBean(ImageService.class);
    }

    @Override
    public ImgBasicOcrResult call() throws Exception {
        ImgBasicOcrResult importResult = new ImgBasicOcrResult();
        importResult.setThreadName(Thread.currentThread().getName());

        // 图片路径
        String imgFilePath = null;

        while ((imgFilePath = imageFilePathQueue.poll()) != null) {

            try {
                importResult.addTotalNum(1);
                byte[] content = FileUtils.getImageBytesWithoutHead(imgFilePath, 55);

                // 进行百度ocr 解析
                OcrResponse ocrResponse = BaiduOcrUtil.doBasicOcr(content);
                String ocr = JSONObject.toJSONString(ocrResponse);

                if (!ImageConstant.TYPE_ANSWERING.equals(ImageUtil.getImageType(ocr))) {
                    log.info("图片非答题图片, 图片:{}");
                    importResult.addIllegalNum(1);
                    continue;
                }

                // 创建图片对象
                ImagePO imagePO = new ImagePO();
                imagePO.setBasicOcr(ocr);
                imagePO.setContent(content);
                imagePO.setStatus(ImageConstant.STATUS_DONE_BASIC_OCR);
                imagePO.setCategory(category);
                imagePO.setCreateTime(new Date());

                // 保存对象
                boolean success = this.imageService.save(imagePO);
                if (success) {
                    importResult.addIncrNum(1);
                }else {
                    importResult.addRepeatNum(1);
                }
            } catch (Exception ex) {
                log.info("保存图片失败, 图片路径:{}, 失败原因:{}", imgFilePath, ex.getMessage());
                importResult.addFailNum(1);
            }
        }

        return importResult;
    }
}
