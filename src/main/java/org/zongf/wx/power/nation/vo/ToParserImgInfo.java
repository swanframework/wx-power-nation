package org.zongf.wx.power.nation.vo;

import java.util.List;

public class ToParserImgInfo {

    // 图片id
    private Long imgId;

    // 文字列表
    private List<String> textLines;

    // 标题行数
    private int titleLines;

    // 剩余数量
    private int leftNum;

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public List<String> getTextLines() {
        return textLines;
    }

    public void setTextLines(List<String> textLines) {
        this.textLines = textLines;
    }

    public int getTitleLines() {
        return titleLines;
    }

    public void setTitleLines(int titleLines) {
        this.titleLines = titleLines;
    }

    public int getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }
}
