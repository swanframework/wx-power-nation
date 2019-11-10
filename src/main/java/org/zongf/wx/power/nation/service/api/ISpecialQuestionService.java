package org.zongf.wx.power.nation.service.api;

import org.zongf.wx.power.nation.vo.SpecialQuestionVO;

import java.util.List;

public interface ISpecialQuestionService {


    /** 查询所有, 条数限制 */
    List<SpecialQuestionVO> queryList(int maxNum);

}
