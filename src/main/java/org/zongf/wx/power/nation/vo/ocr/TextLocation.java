package org.zongf.wx.power.nation.vo.ocr;

/**
 * @author: zongf
 * @created: 2019-10-24
 * @since 1.0
 */
public class TextLocation {

    private int width;

    private int height;

    private int top;

    private int left;

	public TextLocation() {
        super();
    }

	public TextLocation(int width, int height, int top, int left) {
        super();
		this.width = width;
		this.height = height;
		this.top = top;
		this.left = left;
    }

    public void setWidth(int width){
		this.width=width;
	}

	public int getWidth(){
		return this.width;
	}

    public void setHeight(int height){
		this.height=height;
	}

	public int getHeight(){
		return this.height;
	}

    public void setTop(int top){
		this.top=top;
	}

	public int getTop(){
		return this.top;
	}

    public void setLeft(int left){
		this.left=left;
	}

	public int getLeft(){
		return this.left;
	}

    public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {width:" + width + ", height:" + height + ", top:" + top + ", left:" + left  + "}";
	}

}
