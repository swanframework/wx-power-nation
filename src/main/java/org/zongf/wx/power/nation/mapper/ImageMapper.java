package org.zongf.wx.power.nation.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.zongf.wx.power.nation.po.ImagePO;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Mapper
public interface ImageMapper {

    // 保存
    boolean save(ImagePO imagePO);

    // 查询图片信息, 不包含图片内容
    ImagePO queryInfo(Long id);

    // 查询图片内容
    ImagePO queryContent(Long id);

    // 更新状态
    boolean updateStatus(Long id, String status);

    // 删除
    boolean delete(Long id);

    // 分页查询
    PageList<ImagePO> queryByPager(PageBounds pageBounds,
           @Param("category") String category, @Param("status") String status);

    // 清空表
    void clear();

    // 是否有相同Ocr的图片
    boolean hasSameOcr(@Param("category") String category, @Param("basicOcr") String basicOcr);

    // 更新LocOcr 字段
    boolean updateLocOcr(@Param("id") Long id, @Param("locOcr") String locOcr);

}
