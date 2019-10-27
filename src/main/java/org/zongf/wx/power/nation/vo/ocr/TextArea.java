package org.zongf.wx.power.nation.vo.ocr;

/**
 * @author: zongf
 * @created: 2019-10-24
 * @since 1.0
 */
public class TextArea {
    
    private String words;

    // 基础查询返回结果无位置信息
    private TextLocation location;

	public TextArea() {
        super();
    }

	public TextArea(String words, TextLocation location) {
        super();
		this.words = words;
		this.location = location;
    }

    public void setWords(String words){
		this.words=words;
	}

	public String getWords(){
		return this.words;
	}

    public void setLocation(TextLocation location){
		this.location=location;
	}

	public TextLocation getLocation(){
		return this.location;
	}

    public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {words:" + words + ", location:" + location  + "}";
	}

}
