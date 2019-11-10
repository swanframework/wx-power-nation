package org.zongf.wx.power.nation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zongf.wx.power.nation.po.SpecialQuestionPO;
import java.util.List;

@Mapper
public interface SpecialQuestionMapper {

    SpecialQuestionPO queryById(Long id);

    boolean save(SpecialQuestionPO specialQuestionPO);

    boolean delete(Long id);

    List<SpecialQuestionPO> queryAll(int maxNum);

}
