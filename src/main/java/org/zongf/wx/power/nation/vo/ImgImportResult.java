package org.zongf.wx.power.nation.vo;

public class ImgImportResult {

    // 线程名
    private String threadName;

    // 总数量
    private int totalNum;

    // 新增图片数量
    private int incrNum;

    // 重复图片数量
    private int repeatNum;

    // 失败图片数量
    private int failNum;

    // 非法图片数量
    private int illegalNum;

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

    public int getIncrNum() {
        return incrNum;
    }

    public void addIncrNum(int incrNum) {
        this.incrNum += incrNum;
    }

    public int getRepeatNum() {
        return repeatNum;
    }

    public void addRepeatNum(int repeatNum) {
        this.repeatNum += repeatNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void addFailNum(int failNum) {
        this.failNum += failNum;
    }

    public int getIllegalNum() {
        return illegalNum;
    }

    public void addIllegalNum(int illegalNum) {
        this.illegalNum += illegalNum;
    }

    @Override
    public String toString() {
        return "ImgImportResult{" +
                "threadName='" + threadName + '\'' +
                ", totalNum=" + totalNum +
                ", incrNum=" + incrNum +
                ", repeatNum=" + repeatNum +
                ", failNum=" + failNum +
                '}';
    }
}
