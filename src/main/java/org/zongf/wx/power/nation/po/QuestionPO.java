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
	private Long id;

	// 图片id
	private Long imageId;

    // 题目
    private String title;

    // 答案
    private String answer;

    // 答案索引号
    private int answerIdx;

	private String answerLoc;

    // 新增时间
    @JSONField(format="yyyy.MM.dd")
    private Date createTime;

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

	public String getAnswerLoc() {
		return answerLoc;
	}

	public void setAnswerLoc(String answerLoc) {
		this.answerLoc = answerLoc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "QuestionPO{" +
				"id=" + id +
				", imageId=" + imageId +
				", title='" + title + '\'' +
				", answer='" + answer + '\'' +
				", answerIdx=" + answerIdx +
				", answerLoc='" + answerLoc + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
