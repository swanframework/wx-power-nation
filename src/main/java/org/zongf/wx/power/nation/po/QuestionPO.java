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

    // 题目行数
    private int titleLines;

    // 答案索引号
    private int answerIdx;

    // 新增时间
    @JSONField(format="yyyy.MM.dd")
    private Date createTime;

	public QuestionPO() {
        super();
    }

    public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}

    public void setImageId(Long imageId){
		this.imageId=imageId;
	}

	public Long getImageId(){
		return this.imageId;
	}

    public void setTitle(String title){
		this.title=title;
	}

	public String getTitle(){
		return this.title;
	}

    public void setAnswer(String answer){
		this.answer=answer;
	}

	public String getAnswer(){
		return this.answer;
	}

    public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public int getTitleLines() {
		return titleLines;
	}

	public void setTitleLines(int titleLines) {
		this.titleLines = titleLines;
	}

	public int getAnswerIdx() {
		return answerIdx;
	}

	public void setAnswerIdx(int answerIdx) {
		this.answerIdx = answerIdx;
	}

	@Override
	public String toString() {
		return "QuestionPO{" +
				"id=" + id +
				", imageId=" + imageId +
				", title='" + title + '\'' +
				", answer='" + answer + '\'' +
				", titleLines=" + titleLines +
				", answerIdx=" + answerIdx +
				", createTime=" + createTime +
				'}';
	}
}
