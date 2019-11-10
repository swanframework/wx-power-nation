package org.zongf.wx.power.nation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zongf.wx.power.nation.po.WeekQuestionPO;

import java.util.List;

@Mapper
public interface WeekQuestionMapper {

    boolean save(WeekQuestionPO specialQuestionPO);

    boolean delete(Long id);

    List<WeekQuestionPO> queryList(@Param("year") int year, @Param("month") String month);

}
