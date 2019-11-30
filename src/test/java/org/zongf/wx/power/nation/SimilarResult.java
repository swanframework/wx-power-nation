package org.zongf.wx.power.nation;

public class SimilarResult implements Comparable<SimilarResult> {

    private Double similar;

    private long idx;

    private String title;

    public SimilarResult(Double similar, long idx, String title) {
        this.similar = similar;
        this.idx = idx;
        this.title = title;
    }

    public Double getSimilar() {
        return similar;
    }

    public void setSimilar(Double similar) {
        this.similar = similar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    @Override
    public int compareTo(SimilarResult o) {
        return this.similar.compareTo(o.getSimilar());
    }
}
