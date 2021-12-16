package org.spring.demo.score.entity;

public class Counter {
    //真漏洞上报
    public int tp = 0;
    //真漏洞未上报，漏报
    public int fn = 0;
    //假漏洞上报，误报
    public int tn = 0;
    //假漏洞未上报
    public int fp = 0;
}
