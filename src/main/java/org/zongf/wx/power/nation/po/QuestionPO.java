package org.zongf.wx.power.nation.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/** 挑战答题问题
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class QuestionPO implements Serializable {

	// 主键id
	@JSONField(serialize = false)
	private Long id;

	// 图片id
	@JSONField(serialize = false)
	private Long imageId;

    // 题目
    private String title;

    // 题目行数
	@JSONField(serialize = false)
    private int titleLines;

    // 答案
    private String answer;

    // 答案索引号
    private int answerIdx;

    // 新增时间
    @JSONField(format="yyyy.MM.dd")
    private Date createTime;

    // 选项, json数组
    private String options;

	public QuestionPO() {
        super();
    }

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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getAnswerIdx() {
		return answerIdx;
	}

	public void setAnswerIdx(int answerIdx) {
		this.answerIdx = answerIdx;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getTitleLines() {
		return titleLines;
	}

	public void setTitleLines(int titleLines) {
		this.titleLines = titleLines;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "QuestionPO{" +
				"id=" + id +
				", imageId=" + imageId +
				", title='" + title + '\'' +
				", titleLines='" + titleLines + '\'' +
				", answer='" + answer + '\'' +
				", answerIdx=" + answerIdx +
				", createTime=" + createTime +
				'}';
	}
}
