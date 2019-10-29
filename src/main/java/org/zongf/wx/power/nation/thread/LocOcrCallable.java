package org.zongf.wx.power.nation.thread;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.service.impl.ImageService;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.SpringBeanUtil;
import org.zongf.wx.power.nation.vo.ImgBasicOcrResult;
import org.zongf.wx.power.nation.vo.ImgLocOcrResult;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class LocOcrCallable implements Callable<ImgLocOcrResult> {

    private Logger log = LoggerFactory.getLogger(LocOcrCallable.class);

    private IImageService imageService;

    private String category;

    private AtomicInteger pageNum;

    private final int pageSize = 5;

    public LocOcrCallable(String category, AtomicInteger pageNum) {
        this.imageService = SpringBeanUtil.getBean(ImageService.class);
        this.category = category;
        this.pageNum = pageNum;
    }

    @Override
    public ImgLocOcrResult call() throws Exception {

        ImgLocOcrResult ocrResult = new ImgLocOcrResult();
        ocrResult.setThreadName(Thread.currentThread().getName());

        while (true) {
            // 获取下一页
            PageBounds pageBounds = new PageBounds(pageNum.getAndIncrement(), pageSize);

            // 查询下一页 数据
            PageList<ImagePO> pager = this.imageService.queryByPager(pageBounds, category, ImageConstant.STATUS_DONE_BASIC_OCR);

            // 如果查询为空, 则结束
            if (pager == null || pager.size() == 0) {
               break;
            }

            for (ImagePO imagePO : pager) {
                doLocOcr(imagePO, ocrResult);
            }
        }
        return ocrResult;
    }

    public void doLocOcr(ImagePO imagePO, ImgLocOcrResult ocrResult) {

        boolean success = false;
        try {
            // 查询文件字节流
            byte[] content = this.imageService.queryContent(imagePO.getId());

            // 调用百度locOcr 接口
            OcrResponse ocrResponse = BaiduOcrUtil.doLocationOcr(content);

            // 更新数据库
            if (ocrResponse != null) {
                success = this.imageService.updateLocOcr(imagePO.getId(), JSONObject.toJSONString(ocrResponse));
            }
        } catch (Exception ex) {
            log.info("精确OCR失败,图片id:{}", imagePO.getId(), ex);
        } finally {
            if (success) {
                ocrResult.addSuccessNum(1);
            }else {
                ocrResult.addFailNum(1);
            }
            ocrResult.addTotalNum(1);
        }

    }
}
