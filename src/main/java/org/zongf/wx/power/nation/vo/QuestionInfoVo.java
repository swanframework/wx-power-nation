package org.zongf.wx.power.nation.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class QuestionInfoVo {

    // 图片id
    private Long id;

    // 图片路径
    private String imageName;

    // 图片描述
    private List<String> titleList = new ArrayList<>();

    // 图片答案选项
    private List<String> answerList = new ArrayList<>();

	public QuestionInfoVo() {
        super();
    }

	public QuestionInfoVo(Long id, String imageName, List<String> titleList, List<String> answerList) {
        super();
		this.id = id;
		this.imageName = imageName;
		this.titleList = titleList;
		this.answerList = answerList;
    }

    public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}

    public void setImageName(String imageName){
		this.imageName=imageName;
	}

	public String getImageName(){
		return this.imageName;
	}

    public void setTitleList(List<String> titleList){
		this.titleList=titleList;
	}

	public List<String> getTitleList(){
		return this.titleList;
	}

    public void setAnswerList(List<String> answerList){
		this.answerList=answerList;
	}

	public List<String> getAnswerList(){
		return this.answerList;
	}

    public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {id:" + id + ", imageName:" + imageName + ", titleList:" + titleList + ", answerList:" + answerList  + "}";
	}

}
