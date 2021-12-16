package org.spring.demo.score.entity;

public class TestCaseResult {
    private String url;
    private String category;
    private boolean realVul;
    private boolean report;

    public TestCaseResult() {
    }

    public TestCaseResult(String url,String category){
        this.url = url;
        this.category = category;
    }

    public TestCaseResult(String url, String category, boolean realVul, boolean report) {
        this.url = url;
        this.category = category;
        this.realVul = realVul;
        this.report = report;
    }
    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public boolean isRealVul() {
        return realVul;
    }

    public boolean isReport() {
        return report;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRealVul(boolean realVul) {
        this.realVul = realVul;
    }

    public void setReport(boolean report) {
        this.report = report;
    }
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TestCaseResult that = (TestCaseResult) o;
        return url.equals(that.url) &&
                category.equals(that.category);
    }

}
