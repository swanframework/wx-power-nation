package org.zongf.wx.power.nation.po;

import java.util.Arrays;
import java.util.Date;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class ImagePO {

    // 图片id
    private Long id;

    // 图片内容, 字节码
    private byte[] content;

    // 图片类型: 1 挑战答题  2 每周答题  3 专项答题
    private String category;

    // 低精度ocr
    private String basicOcr;

    // 高精度ocr, 包含位置
    private String locOcr;

    // 图片状态: 0 待处理 1 已经处理
    private String status;

    // 创建时间
    private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBasicOcr() {
		return basicOcr;
	}

	public void setBasicOcr(String basicOcr) {
		this.basicOcr = basicOcr;
	}

	public String getLocOcr() {
		return locOcr;
	}

	public void setLocOcr(String locOcr) {
		this.locOcr = locOcr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ImagePO{" +
				"id=" + id +
				", content=" + Arrays.toString(content) +
				", type='" + category + '\'' +
				", basicOcr='" + basicOcr + '\'' +
				", locOrc='" + locOcr + '\'' +
				", status='" + status + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
