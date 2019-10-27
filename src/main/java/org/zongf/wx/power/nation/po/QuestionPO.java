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

    // 新增时间
    @JSONField(format="yyyy.MM.dd")
    private Date createTime;

	public QuestionPO() {
        super();
    }

	public QuestionPO(Long id, Long imageId, String title, String answer, Date createTime) {
        super();
		this.id = id;
		this.imageId = imageId;
		this.title = title;
		this.answer = answer;
		this.createTime = createTime;
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

    public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {id:" + id + ", imageId:" + imageId + ", title:" + title + ", answer:" + answer + ", createTime:" + createTime  + "}";
	}

}
