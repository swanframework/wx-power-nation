package org.zongf.wx.power.nation.service.impl;

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
import org.zongf.wx.power.nation.vo.TodoImageInfoVO;

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
        PageList<ImagePO> pager = this.imageMapper.queryByPager(new PageBounds(page++, 1), type, ImageConstant.STATUS_DONE_BASIC_OCR);

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
        return this.imageMapper.updateLocOcr(id, locOcr);
    }

}
