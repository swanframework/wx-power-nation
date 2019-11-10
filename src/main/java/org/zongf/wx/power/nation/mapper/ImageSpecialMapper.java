package org.zongf.wx.power.nation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zongf.wx.power.nation.po.ImageSpecialPO;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Mapper
public interface ImageSpecialMapper {

    // 保存
    boolean save(ImageSpecialPO commonImagePO);

    // 查询图片信息, 不包含图片内容
    ImageSpecialPO queryInfo(Long id);

    ImageSpecialPO queryNextTodo();

    boolean handleImage(Long id);

}
