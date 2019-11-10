package org.zongf.wx.power.nation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zongf.wx.power.nation.po.SpecialItemPO;
import org.zongf.wx.power.nation.po.WeekItemPO;

import java.util.List;

@Mapper
public interface WeekItemMapper {

    boolean save(WeekItemPO weekItemPO);

    List<WeekItemPO> queryList(long weekId);

}
