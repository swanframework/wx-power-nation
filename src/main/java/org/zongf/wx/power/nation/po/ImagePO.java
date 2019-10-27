package org.zongf.wx.power.nation.po;

import java.util.Date;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class ImagePO {

    // 图片id
    private Long id;

    // 图片名称
    private String name;

    // 图片内容, 字节码
    private byte[] content;

    // 图片类型: 1 挑战答题  2 每周答题  3 专项答题
    private String type;

    // 图片ocr 结果
    private String ocr;

    // 图片状态: 0 待处理 1 已经处理
    private String status = "0";

    // 创建时间
    private Date createTime;

	public ImagePO() {
        super();
    }

	public ImagePO(Long id, String name, byte[] content, String type, String ocr, String status, Date createTime) {
        super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.type = type;
		this.ocr = ocr;
		this.status = status;
		this.createTime = createTime;
    }

    public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}

    public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

    public void setContent(byte[] content){
		this.content=content;
	}

	public byte[] getContent(){
		return this.content;
	}

    public void setType(String type){
		this.type=type;
	}

	public String getType(){
		return this.type;
	}

    public void setOcr(String ocr){
		this.ocr=ocr;
	}

	public String getOcr(){
		return this.ocr;
	}

    public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

    public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

    public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {id:" + id + ", name:" + name + ", content:" + content + ", type:" + type + ", ocr:" + ocr + ", status:" + status + ", createTime:" + createTime  + "}";
	}

}
