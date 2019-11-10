package org.zongf.wx.power.nation.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WeekQuestionPO {

    // 主键ID
    private Long id;

    // 名称
    private String name;

    // 年
    private String year;

    // 月
    private String month;

    // 序号
    private int weekNo;

    // 创建时间
    @JSONField(serialize = false)
    private Date createTime;

    public WeekQuestionPO() {
    }


    public WeekQuestionPO(String year, String month, int weekNo) {
        this.year = year;
        this.month = month;
        this.weekNo = weekNo;
        this.createTime = new Date();

        // 月份取两位数
        StringBuffer nameSb = new StringBuffer();
        nameSb.append(this.year).append("年").append(month)
              .append("月第").append(getWeekNoUpperCase(weekNo)).append("周答题");

        this.name = nameSb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String getWeekNoUpperCase(int weekNo){

        switch (weekNo) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
        }

        return null;
    }

    @Override
    public String toString() {
        return "WeekQuestionPO{" + "id=" + id + ", name='" + name + '\'' + ", year='" + year + '\'' + ", month='" + month + '\'' + ", weekNo=" + weekNo + ", createTime=" + createTime + '}';
    }
}
