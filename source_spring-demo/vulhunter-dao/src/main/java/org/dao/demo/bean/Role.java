package org.dao.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "role")
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_zh")
    private String name_zh;

    @Column(name = "username")
    private String username;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return "Role(id=" + getId() + ", name=" + getName() + ", name_zh=" + getName_zh() + ", username=" + getUsername() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getName_zh() {
        return this.name_zh;
    }

    public String getUsername() {
        return this.username;
    }
}
