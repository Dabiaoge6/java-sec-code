package com.seczone.security.dao;


public class VulType {
    private Integer level;
    private Integer fatherid;
    private String href;
    private String introduce;
    private String id;
    private String vulname;

    public String getVulname() {
        return vulname;
    }

    public void setVulname(String vulname) {
        this.vulname = vulname;
    }

    @Override
    public String toString() {
        return "VulType{" +
                "level=" + level +
                ", fatherid=" + fatherid +
                ", href='" + href + '\'' +
                ", introduce='" + introduce + '\'' +
                ", id='" + id + '\'' +
                ", vulname='" + vulname + '\'' +
                '}';
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
