package org.zongf.wx.power.nation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zongf.wx.power.nation.mapper.WeekItemMapper;
import org.zongf.wx.power.nation.mapper.WeekQuestionMapper;
import org.zongf.wx.power.nation.po.SpecialItemPO;
import org.zongf.wx.power.nation.po.SpecialQuestionPO;
import org.zongf.wx.power.nation.po.WeekItemPO;
import org.zongf.wx.power.nation.po.WeekQuestionPO;
import org.zongf.wx.power.nation.service.api.IWeekQuestionService;
import org.zongf.wx.power.nation.vo.MonthQuestionVO;
import org.zongf.wx.power.nation.vo.SpecialQuestionVO;
import org.zongf.wx.power.nation.vo.WeekQuestionVO;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeekQuestionService implements IWeekQuestionService {

    @Resource
    private WeekQuestionMapper weekQuestionMapper;

    @Autowired
    private WeekItemMapper weekItemMapper;

    private MonthQuestionVO queryMonthQuestion(int preMonths) {

        // 计算月份日期
        LocalDate preMonthDate = LocalDate.now().minusMonths(preMonths);

        int year = preMonthDate.getYear();
        String month = preMonthDate.getMonthValue() > 9 ? "" + preMonthDate.getMonthValue() : "0" + preMonthDate.getMonthValue();

        List<WeekQuestionVO> weekQuestionVOList = new ArrayList<>();

        // 查询当月所有的周问题
        List<WeekQuestionPO> weekQuestionList = this.weekQuestionMapper.queryList(year, month);

        for (WeekQuestionPO weekQuestionPO : weekQuestionList) {
            List<WeekItemPO> weekItemList = this.weekItemMapper.queryList(weekQuestionPO.getId());
            weekQuestionVOList.add(new WeekQuestionVO(weekQuestionPO, weekItemList));
        }

        return new MonthQuestionVO(year, month, weekQuestionVOList);

    }

    @Override
    public List<MonthQuestionVO> queryList(int maxNum) {

        List<MonthQuestionVO> monthList = new ArrayList<>();

        monthList.add(queryMonthQuestion(0));
        monthList.add(queryMonthQuestion(1));
        monthList.add(queryMonthQuestion(2));

        return monthList;
    }

    public static void main(String[] args) {

        int month = LocalDate.now().minusMonths(3l).getMonth().getValue();

        System.out.println(month);
    }
}
