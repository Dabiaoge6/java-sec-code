package org.spring.demo.score.entity;

public class VulnerCounter {
    private String category;
    private int total;
    private int tp = 0;
    //真漏洞未上报，漏报
    private int fn = 0;
    //假漏洞上报，误报
    private int tn = 0;
    //假漏洞未上报
    private int fp = 0;
    private String tpr;
    private String fpr;
    private String score;

    public VulnerCounter() {
    }

    public int getTotal() {
        return total;
    }

    public int getTp() {
        return tp;
    }

    public int getFn() {
        return fn;
    }

    public int getTn() {
        return tn;
    }

    public int getFp() {
        return fp;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public void setFn(int fn) {
        this.fn = fn;
    }

    public void setTn(int tn) {
        this.tn = tn;
    }

    public void setFp(int fp) {
        this.fp = fp;
    }

    public String getTpr() {
        return tpr;
    }

    public String getFpr() {
        return fpr;
    }

    public String getScore() {
        return score;
    }

    public void setTpr(String tpr) {
        this.tpr = tpr;
    }

    public void setFpr(String fpr) {
        this.fpr = fpr;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
