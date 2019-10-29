package org.zongf.wx.power.nation.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.thread.OcrTask;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.vo.LatestImageInfo;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public LatestImageInfo queryToDoImage(String type) {
        PageList<ImagePO> imagePOS = this.imageMapper.queryByPager(new PageBounds(1, 1), type, ImageConstant.STATUS_TODO);
        if(imagePOS.isEmpty()) return null;
        return new LatestImageInfo(imagePOS.get(0),imagePOS.getPaginator().getTotalCount());
    }

    @Override
    public boolean handleImage(Long id) {
        return this.imageMapper.updateStatus(id, ImageConstant.STATUS_HANDLED);
    }

}
