package org.zongf.wx.power.nation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zongf.wx.power.nation.po.ImageSpecialPO;
import org.zongf.wx.power.nation.po.ImageWeekPO;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Mapper
public interface ImageWeekMapper {

    // 保存
    boolean save(ImageWeekPO commonImagePO);

    // 查询图片信息, 不包含图片内容
    ImageWeekPO queryInfo(Long id);

    ImageWeekPO queryNextTodo();

    boolean handleImage(Long id);

}
