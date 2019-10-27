package org.zongf.wx.power.nation.thread;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.po.QuestionPO;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
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

    private ConcurrentLinkedQueue<ImagePO> imageQueue;

    private ImageMapper imageMapper;

    public OcrRunnable(ConcurrentLinkedQueue<ImagePO> imageQueue) {
        this.imageQueue = imageQueue;
        this.imageMapper = SpringBeanUtil.getBean(ImageMapper.class);
    }

    @Override
    public void run() {

        ImagePO imagePO = null;

        while ((imagePO = imageQueue.poll()) != null) {

            // 进行百度ocr 解析
            OcrResponse ocrResponse = BaiduOcrUtil.doBasicOcr(imagePO.getContent());

            // 处理第一行信息, 如果以NB开头, 则表示为时间状态栏
            String firstLine = ocrResponse.getWords_result().get(0).getWords();
            if (firstLine.startsWith("NB")) {
                ocrResponse.getWords_result().remove(0);
            }

            try {
                String ocr = JSONObject.toJSONString(ocrResponse);
                // 如果不存在相同的ocr, 则入库

                // 如果图片中包含"结束本局"关键字, 则为错误图片,忽略
                if (ocr.contains("结束本局")) {
                    log.info("错误图片, 图片信息:{}", imagePO);
                    continue;
                }

                // 如果图片中已存在相同的ocr, 则忽略
                if (imageMapper.hasSameOcr(imagePO.getType(), ocr)) {
                    log.info("保存图片失败, 图片库中已存在相同类型且相同ocr的记录, 图片信息:{}", imagePO);
                }else {
                    imagePO.setOcr(ocr);
                    imagePO.setCreateTime(new Date());
                    this.imageMapper.save(imagePO);
                }
            } catch (Exception ex) {
                log.info("保存图片失败, 图片路径:{}, 失败原因:{}", imagePO, ex.getMessage());
            }
        }
    }
}
