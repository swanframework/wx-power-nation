package org.zongf.wx.power.nation.thread;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
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

    private ConcurrentLinkedQueue<String> fileQueue;

    private QuestionMapper questionMapper;

    public OcrRunnable(ConcurrentLinkedQueue<String> fileQueue) {
        this.fileQueue = fileQueue;
        this.questionMapper = SpringBeanUtil.getBean(QuestionMapper.class);
    }

    @Override
    public void run() {

        String imagePath = null;

        while ((imagePath = fileQueue.poll()) != null) {

            // 进行百度ocr 解析
            OcrResponse ocrResponse = BaiduOcrUtil.doBasicOcr(imagePath);

            // 处理第一行信息, 如果以NB开头, 则表示为时间状态栏
            String firstLine = ocrResponse.getWords_result().get(0).getWords();
            if (firstLine.startsWith("NB")) {
                ocrResponse.getWords_result().remove(0);
            }

            try {
                QuestionPO questionPO = new QuestionPO();
                questionPO.setImageName(StringUtils.substringAfterLast(imagePath, "/"));
                questionPO.setCreateTime(new Date());
                questionPO.setOcr(JSONObject.toJSONString(ocrResponse));
                this.questionMapper.save(questionPO);
            } catch (Exception ex) {
                log.info("保存图片失败, 图片路径:{}, 失败原因:{}", imagePath, ex.getMessage());
            }
        }
    }
}
