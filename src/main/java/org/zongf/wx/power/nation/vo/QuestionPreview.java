package org.zongf.wx.power.nation.vo;

import org.zongf.wx.power.nation.po.QuestionPO;

import java.util.List;

public class QuestionPreview {

    private Long id;

    private Long imageId;

    private String title;

    private List<String> options;

    private int answerIdx;

    private int titleLines;

    private int page;

    private int totalPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getAnswerIdx() {
        return answerIdx;
    }

    public void setAnswerIdx(int answerIdx) {
        this.answerIdx = answerIdx;
    }

    public int getTitleLines() {
        return titleLines;
    }

    public void setTitleLines(int titleLines) {
        this.titleLines = titleLines;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
