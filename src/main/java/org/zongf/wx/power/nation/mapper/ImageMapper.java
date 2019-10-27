package org.zongf.wx.power.nation.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    // 删除
    boolean delete(Long id);

    // 通过id查询
    ImagePO findById(Long id);

    // 分页查询
    PageList<ImagePO> queryByPager(PageBounds pageBounds,
           @Param("type") String type, @Param("status") String status);

    // 清空表
    void clear();

    // 是否有相同名称的图片
    boolean hasSameName(String name);

    // 是否有相同Ocr的图片
    boolean hasSameOcr(@Param("type") String type, @Param("ocr") String ocr);

}
