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
	private Integer id;

	// 图片路径
	private String imageName;

    // 题目
    private String title;

    // 答案
    private String answer;

    // 百度ocr解析结果,用于去重
    @JSONField(serialize=false)
    private String ocr;

    // 新增时间
    @JSONField(format="yyyy.MM.dd")
    private Date createTime;

	public QuestionPO() {
        super();
    }

	public QuestionPO(Integer id, String imagePath, String title, String answer, String ocr, Date createTime) {
        super();
		this.id = id;
		this.imageName = imagePath;
		this.title = title;
		this.answer = answer;
		this.ocr = ocr;
		this.createTime = createTime;
    }

    public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

    public void setImageName(String imageName){
		this.imageName = imageName;
	}

	public String getImageName(){
		return this.imageName;
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

    public void setOcr(String ocr){
		this.ocr=ocr;
	}

	public String getOcr(){
		return this.ocr;
	}

    public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

    public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {id:" + id + ", imageName:" + imageName + ", title:" + title + ", answer:" + answer + ", ocr:" + ocr + ", createTime:" + createTime  + "}";
	}

}
