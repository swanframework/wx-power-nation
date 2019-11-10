package org.zongf.wx.power.nation.vo;

import java.util.List;

public class MonthQuestionVO {

    private int year;

    private String month;

    private List<WeekQuestionVO> weekList;

    public MonthQuestionVO() {
    }

    public MonthQuestionVO(int year, String monty, List<WeekQuestionVO> weekList) {
        this.year = year;
        this.month = monty;
        this.weekList = weekList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<WeekQuestionVO> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<WeekQuestionVO> weekList) {
        this.weekList = weekList;
    }
}
