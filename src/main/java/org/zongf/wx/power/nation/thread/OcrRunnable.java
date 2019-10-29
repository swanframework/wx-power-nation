package org.zongf.wx.power.nation.thread;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.service.impl.ImageService;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.util.ImageUtil;
import org.zongf.wx.power.nation.util.SpringBeanUtil;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class OcrRunnable implements Runnable{

    private Logger log = LoggerFactory.getLogger(OcrRunnable.class);

    private ConcurrentLinkedQueue<String> imageFilePathQueue;

    private IImageService imageService;

    private String category;

    public OcrRunnable(ConcurrentLinkedQueue<String> imageQueue, String category) {
        this.imageFilePathQueue = imageQueue;
        this.category = category;
        this.imageService = SpringBeanUtil.getBean(ImageService.class);
    }

    @Override
    public void run() {

        String imgFilePath = null;

        while ((imgFilePath = imageFilePathQueue.poll()) != null) {

            byte[] content = FileUtils.getFileBytes(imgFilePath);

            // 进行百度ocr 解析
            OcrResponse ocrResponse = BaiduOcrUtil.doBasicOcr(content);

            try {
                String ocr = JSONObject.toJSONString(ocrResponse);

                if (!ImageConstant.TYPE_ANSWERING.equals(ImageUtil.getImageType(ocr))) {
                    log.info("图片非答题图片, 图片:{}");
                    continue;
                }

                ImagePO imagePO = new ImagePO();
                imagePO.setBasicOcr(ocr);
                imagePO.setContent(content);
                imagePO.setStatus(ImageConstant.STATUS_TODO);
                imagePO.setCategory(category);
                imagePO.setCreateTime(new Date());

                this.imageService.save(imagePO);

            } catch (Exception ex) {
                log.info("保存图片失败, 图片路径:{}, 失败原因:{}", imgFilePath, ex.getMessage());
            }
        }
    }
}
