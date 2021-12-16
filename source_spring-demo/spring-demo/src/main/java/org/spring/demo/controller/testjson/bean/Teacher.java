package org.spring.demo.controller.testjson.bean;

import java.util.ArrayList;
import java.util.List;
import org.spring.demo.controller.testjson.TestJsonController;

public class Teacher {
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
  private List<Student> students = new ArrayList<Student>();

  public Teacher(){
    TestJsonController.counter++;
    this.id = 1;
    this.name = "t1";
    this.age = 23;
    this.mobile="18343242394";
    this.address="home";
    this.hobby="booke";
    this.sex=false;
    this.work="teacher";
    this.test="testTeacher";
    this.text="textTeacher";
    this.className="teacher";
    this.classNum="21";
    if(TestJsonController.counter >=2000){
      return;
    }
    Student student = new Student();
    student.setId(111);
    student.setName("student1");
    List<Student> students = new ArrayList<Student>();
    students.add(student);
    this.students = students;

  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
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
}
