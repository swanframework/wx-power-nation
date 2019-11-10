package org.zongf.wx.power.nation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zongf.wx.power.nation.po.SpecialItemPO;

import java.util.List;

@Mapper
public interface SpecialItemMapper {

    boolean save(SpecialItemPO questionItemPO);

    List<SpecialItemPO> queryList(long specialId);

}
