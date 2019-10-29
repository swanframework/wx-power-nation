package org.zongf.wx.power.nation.vo;

public class ImgLocOcrResult {

    // 线程名
    private String threadName;

    // 总数量
    private int totalNum;

    // 新增图片数量
    private int successNum;

    // 失败图片数量
    private int failNum;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void addTotalNum(int totalNum) {
        this.totalNum += totalNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void addFailNum(int failNum) {
        this.failNum += failNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void addSuccessNum(int successNum) {
        this.successNum += successNum;
    }
}
