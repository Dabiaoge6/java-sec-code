package org.dao.demo.bean;

public class User {
    private String id;
    private String name;
    private String pwd;
    private int age;
    private String tel;
    private String role;

    public User() {
        super();
    }

    public User(String id, String name, String pwd, int age, String tel, String role) {
        super();
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.age = age;
        this.tel = tel;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
}
