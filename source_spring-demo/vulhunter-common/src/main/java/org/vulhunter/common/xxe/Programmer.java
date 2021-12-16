package org.vulhunter.common.xxe;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Programmer {
    
    private String id;
    private int age;
    private String name;
    private String email;
    public String getId() {
        return id;
    }
    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Programmer [id=" + id + ", age=" + age + ", name=" + name + ", email=" + email
                + "]";
    }
}
