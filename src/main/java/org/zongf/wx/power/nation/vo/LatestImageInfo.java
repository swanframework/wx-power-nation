package org.zongf.wx.power.nation.vo;

import org.zongf.wx.power.nation.po.ImagePO;

public class LatestImageInfo {

    private ImagePO imagePO;

    private int totalNum;

    public LatestImageInfo() {
    }

    public LatestImageInfo(ImagePO imagePO, int totalNum) {
        this.imagePO = imagePO;
        this.totalNum = totalNum;
    }

    public ImagePO getImagePO() {
        return imagePO;
    }

    public void setImagePO(ImagePO imagePO) {
        this.imagePO = imagePO;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
