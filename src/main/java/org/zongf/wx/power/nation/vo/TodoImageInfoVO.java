package org.zongf.wx.power.nation.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class TodoImageInfoVO {

    // 图片id
    private Long id;

    // 图片路径
    private String imageName;

    // 图片描述
    private List<String> titleList = new ArrayList<>();

    // 图片答案选项
    private List<String> answerList = new ArrayList<>();

    private int todoNum;

    private List<String> lineList;

	public TodoImageInfoVO() {
        super();
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

	public int getTodoNum() {
		return todoNum;
	}

	public void setTodoNum(int todoNum) {
		this.todoNum = todoNum;
	}

	public List<String> getLineList() {
		return lineList;
	}

	public void setLineList(List<String> lineList) {
		this.lineList = lineList;
	}

	public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {id:" + id + ", imageName:" + imageName + ", titleList:" + titleList + ", answerList:" + answerList  + "}";
	}

}
