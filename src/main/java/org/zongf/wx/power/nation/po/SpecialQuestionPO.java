package org.zongf.wx.power.nation.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SpecialQuestionPO {

    // 主键ID
    private Long id;

    // 名称
    private String name;

    // 开始日期
    private String startDate;

    // 结束日期
    private String endDate;

    // 创建时间
    @JSONField(serialize = false)
    private Date createTime;

    public SpecialQuestionPO() {
    }

    public SpecialQuestionPO(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createTime = new Date();
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SpecialQuestionPO{" + "id=" + id + ", name='" + name + '\'' + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + ", createTime=" + createTime + '}';
    }
}
