package com.seczone.security.dao;

public class Http {
    private String ip;
    private String user_agent;
    private String httpid;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getHttpid() {
        return httpid;
    }

    public void setHttpid(String httpid) {
        this.httpid = httpid;
    }

    @Override
    public String toString() {
        return "Http{" +
                "ip='" + ip + '\'' +
                ", user_agent='" + user_agent + '\'' +
                ", httpid='" + httpid + '\'' +
                '}';
    }
}
