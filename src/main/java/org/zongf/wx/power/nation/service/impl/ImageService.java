package org.zongf.wx.power.nation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.factory.TodoImageFactory;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.ThreadUtil;
import org.zongf.wx.power.nation.vo.ImgLocOcrResult;
import org.zongf.wx.power.nation.vo.TodoImageInfoVO;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Service
public class ImageService implements IImageService {

    private static Logger log = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public synchronized boolean save(ImagePO imagePO) {
        if (this.imageMapper.hasSameOcr(imagePO.getCategory(), imagePO.getBasicOcr())) {
            log.warn("图片已存在");
            return false;
        }
        return this.imageMapper.save(imagePO);
    }

    @Override
    public boolean delete(Long id) {
        return this.imageMapper.delete(id);
    }

    @Override
    public ImagePO queryInfo(Long id) {
        return this.imageMapper.queryInfo(id);
    }

    @Override
    public byte[] queryContent(Long id) {
        ImagePO imagePO = this.imageMapper.queryContent(id);
        return imagePO == null ? null : imagePO.getContent();
    }

    private static int page = 1;
    @Override
    public TodoImageInfoVO queryToDoImage(String type) {
        // 使用分页查询, 查询1页信息
        PageList<ImagePO> pager = this.imageMapper.queryByPager(new PageBounds(page++, 1), type, ImageConstant.STATUS_DONE_LOC_OCR);

        // 如果为空, 则返回null
        if(pager.isEmpty()) return null;

        return TodoImageFactory.create(pager.get(0), pager.getPaginator().getTotalCount());
    }

    @Override
    public boolean handleImage(Long id) {
        return this.imageMapper.updateStatus(id, ImageConstant.STATUS_CONVERTED_QUESTION);
    }

    @Override
    public PageList<ImagePO> queryByPager(PageBounds pageBounds, String category, String status) {
        return this.imageMapper.queryByPager(pageBounds, category, status);
    }

    @Override
    public boolean updateLocOcr(Long id, String locOcr) {
        return this.imageMapper.updateAccurateOcr(id, locOcr);
    }

    @Override
    public boolean batchAccurateOcr(String category) {
        ImgLocOcrResult ocrResult = new ImgLocOcrResult();
        ImagePO imagePO = null;
        boolean success = false;
        while (true) {
            try {

                // 查询下一条待做精确ocr 的记录
                imagePO = this.imageMapper.queryNextToDoAccurateOcr(category);

                // 查询结果为空, 则表示已无记录
                if (imagePO == null) break;

                // 调用百度locOcr 接口
                OcrResponse ocrResponse = BaiduOcrUtil.doBasicAccurateOcr(imagePO.getContent());

                // 更新数据库
                if (ocrResponse != null) {
                    success = this.updateLocOcr(imagePO.getId(), JSONObject.toJSONString(ocrResponse));
                    log.info("图片精确ocr成功, 图片id:{}", imagePO.getId());
                }
            } catch (Exception ex) {
                log.error("图片精确ocr失败", ex);
            }finally {
                if (success) {
                    ocrResult.addSuccessNum(1);
                }else {
                    ocrResult.addFailNum(1);
                }
                ocrResult.addTotalNum(1);
            }
            ThreadUtil.sleep(0.3f);
        }

        log.info("图片精确ocr结束: 总数量:{}, 成功数量:{}, 失败数量:{}",
                ocrResult.getTotalNum(), ocrResult.getSuccessNum(), ocrResult.getFailNum());

        return false;
    }

    public void doLocOcr(ImagePO imagePO, ImgLocOcrResult ocrResult) {

        boolean success = false;
        try {
            // 查询文件字节流
            byte[] content = this.queryContent(imagePO.getId());

            // 调用百度locOcr 接口
            OcrResponse ocrResponse = BaiduOcrUtil.doBasicAccurateOcr(content);

            // 更新数据库
            if (ocrResponse != null) {
                success = this.updateLocOcr(imagePO.getId(), JSONObject.toJSONString(ocrResponse));
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
