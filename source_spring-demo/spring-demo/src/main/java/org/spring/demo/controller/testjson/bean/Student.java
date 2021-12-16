package org.spring.demo.controller.testjson.bean;

import java.util.ArrayList;
import java.util.List;
import org.spring.demo.controller.testjson.TestJsonController;

public class Student {

  private String protectedKey;
  private int id;
  private String name;
  private int age;
  private String mobile;
  private String address;
  private String hobby;
  private boolean sex;
  private String work;
  private String test;
  private String text;
  private String className;
  private String classNum;
  private List<Teacher> teachers = new ArrayList<Teacher>();

  public Student() {
    TestJsonController.counter++;
    this.protectedKey = "164832849230";
    this.id = 2;
    this.name = "s1";
    this.age = 23;
    this.mobile = "18343242394";
    this.address = "home";
    this.hobby = "booke";
    this.sex = false;
    this.work = "teacher";
    this.test = "testTeacher";
    this.text = "textTeacher";
    this.className = "teacher";
    this.classNum = "21";
    if (TestJsonController.counter >= 2000) {
      return;
    }
    Teacher teacher = new Teacher();
    teacher.setId(100);
    teacher.setName("teacher1");
    List<Teacher> teachers = new ArrayList<Teacher>();
    teachers.add(teacher);
    this.teachers = teachers;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Teacher> getTeachers() {
    return teachers;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTeachers(List<Teacher> teachers) {
    this.teachers = teachers;
  }

  public int getAge() {
    return age;
  }

  public String getMobile() {
    return mobile;
  }

  public String getAddress() {
    return address;
  }

  public String getHobby() {
    return hobby;
  }

  public boolean isSex() {
    return sex;
  }

  public String getWork() {
    return work;
  }

  public String getTest() {
    return test;
  }

  public String getText() {
    return text;
  }

  public String getClassName() {
    return className;
  }

  public String getClassNum() {
    return classNum;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setHobby(String hobby) {
    this.hobby = hobby;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  public void setWork(String work) {
    this.work = work;
  }

  public void setTest(String test) {
    this.test = test;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public void setClassNum(String classNum) {
    this.classNum = classNum;
  }

  public String getProtectedKey() {
    return protectedKey;
  }

  public void setProtectedKey(String protectedKey) {
    this.protectedKey = protectedKey;
  }
}
