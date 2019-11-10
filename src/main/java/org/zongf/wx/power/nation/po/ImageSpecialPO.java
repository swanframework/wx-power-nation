package org.zongf.wx.power.nation.po;

import java.util.Arrays;
import java.util.Date;

public class ImageSpecialPO {

    private Long id;

    private Long specialId;

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

    public ImageSpecialPO() {
    }

    public ImageSpecialPO(byte[] content) {
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

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
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
