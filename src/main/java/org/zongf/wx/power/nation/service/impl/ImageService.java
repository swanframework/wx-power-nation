package org.zongf.wx.power.nation.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.thread.OcrTask;
import org.zongf.wx.power.nation.util.FileUtils;

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
    public ImagePO queryById(Long id) {
        return this.imageMapper.findById(id);
    }

    @Override
    public PageList<ImagePO> queryPager(PageBounds pageBounds, String type, String status) {
        return this.imageMapper.queryByPager(pageBounds, type, status);
    }

    @Override
    public boolean delete(Long id) {
        return this.imageMapper.delete(id);
    }

    @Override
    public boolean batchSave(String path, String type) {

        // 1. 比较数据库去重
        List<String> fileNames = FileUtils.getFileNames(path);

        // 处理新增的图片
        List<ImagePO> imagePOList = new ArrayList<>();
        for (String filePath : fileNames) {
            String fileName = StringUtils.substringAfterLast(filePath, "/");
            if (this.imageMapper.hasSameName(fileName)) {
                log.info("图片库中已存在相同名称的图片:{}",fileName);
            }else {
                ImagePO imagePO = new ImagePO();
                imagePO.setContent(FileUtils.getFileBytes(filePath));
                imagePO.setName(fileName);
                imagePO.setType(type);
                imagePOList.add(imagePO);
            }
        }

        // 2. 多线程调用百度ocr
        if (imagePOList.size() > 0) {
            OcrTask.doOcrTask(imagePOList);
        }

        return false;
    }
}
