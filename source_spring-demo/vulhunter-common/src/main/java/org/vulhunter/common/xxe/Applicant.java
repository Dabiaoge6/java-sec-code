package org.vulhunter.common.xxe;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "applicant")
public class Applicant {
    private int id;
    private String uuid;
    private String name;
    private int age;
    private String mobile;
    private String email;
    private String address;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        UUID uuid1 = UUID.randomUUID();
        return uuid1.toString().replace("-", "");
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    @XmlElement(name = "mobile")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement(name = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Applicant [id=" + id + ", uuid=" + uuid + ", name=" + name + ", age=" + age
                + ", mobile=" + mobile + ", email=" + email + ", address=" + address + ", status="
                + status + "]";
    }

}
