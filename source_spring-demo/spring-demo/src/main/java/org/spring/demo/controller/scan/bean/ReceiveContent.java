package org.spring.demo.controller.scan.bean;


public class ReceiveContent {

	private String name;

	private String pwd;
	
	private int age;
	
	private String path;
	
	private TrunkContent trunkContent;

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrunkContent getTrunkContent() {
		return trunkContent;
	}

	public void setTrunkContent(TrunkContent trunkContent) {
		this.trunkContent = trunkContent;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
