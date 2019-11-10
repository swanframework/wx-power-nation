package org.zongf.wx.power.nation.service.api;

import org.zongf.wx.power.nation.vo.MonthQuestionVO;
import org.zongf.wx.power.nation.vo.SpecialQuestionVO;

import java.util.List;

public interface IWeekQuestionService {


    /** 查询所有, 条数限制 */
    List<MonthQuestionVO> queryList(int maxNum);

}
