package org.zongf.wx.power.nation.po;

import java.util.Arrays;
import java.util.Date;

public class ImageWeekPO {

    private Long id;

    private Long weekId;

    private byte[] content;

    private String titles;

    private String options;

    private String type;

    private int seqNo;

    private String status;

    private Date createTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ImageWeekPO() {
    }

    public ImageWeekPO(byte[] content) {
        this.content = content;
        this.createTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getOptions() {
        return options;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getWeekId() {
        return weekId;
    }

    public void setWeekId(Long weekId) {
        this.weekId = weekId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ImageSpecialPO{" + "id=" + id + ", content=" + Arrays.toString(content) + ", createTime=" + createTime + '}';
    }
}
