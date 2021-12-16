package org.spring.demo.controller.testjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.service.demo.service.UserService;
import org.spring.demo.controller.testjson.bean.Student;
import org.spring.demo.controller.testjson.bean.Student1;
import org.spring.demo.controller.testjson.bean.Teacher;
import org.spring.demo.controller.testjson.bean.User;
import org.spring.demo.controller.testjson.bean.User1;
import org.spring.demo.controller.testjson.bean.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("testJson")
public class TestJsonController {

  @Autowired
  private UserService userService;
  public static int counter = 0;

  public static void main(String... ar) throws Exception {
    /*User user = new User();
    user.setUser1(new User1());
    user.setUser2(new User2());
    String jsonStr = JSON.toJSONString(user);
    *//*ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = objectMapper.writeValueAsString(user);*//*
    System.out.println(jsonStr);*/

    //第二种方式
    /*Student student = new Student();
    student.setId(111);
    student.setName("student1");
    List<Student> students = new ArrayList<Student>();
    students.add(student);
    Teacher teacher = new Teacher();
    teacher.setId(100);
    teacher.setName("teacher1");
    List<Teacher> teachers = new ArrayList<Teacher>();
    teachers.add(teacher);
    student.setTeachers(teachers);
    teacher.setStudents(students);

    //转换成json
    System.out
        .println("JSON=" + JSON.toJSONString(student, SerializerFeature.DisableCheckSpecialChar));
    System.out.println("JSON="+JSON.toJSONString(student));
    System.out.println("Gson="+new Gson().toJson(student));*/
    /*System.out
        .println("JSON=" + JSON.toJSONString(student, SerializerFeature.DisableCircularReferenceDetect));*/
    Student student = new Student();
    String jsonStr = JSON.toJSONString(student);
    File file = new File("C:\\Users\\ThinkPad\\Desktop\\json.txt");

    // if file doesnt exists, then create it
    if (!file.exists()) {
      file.createNewFile();
    }

    FileWriter fw = new FileWriter(file.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(jsonStr);
    bw.close();

    //字符串转换成对象
    Set<Entry<String, Object>> entrys = JSONObject.parseObject(jsonStr).entrySet();
    /*Map<String,String> maps = new JsonUtils().toMap(jsonStr);
    Set<Entry<String, String>> entrys =maps.entrySet();*/
    for (Entry<String, Object> entry : entrys) {
      String key = entry.getKey();
      Object value = entry.getValue();
      System.out.println(key + ":" + String.valueOf(value));
    }
  }

  @RequestMapping(value = "/testProtectedKey.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
  public void testProtectedKey(@RequestBody Student student, HttpServletRequest request,
      HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    /*User user = JSONObject.parseObject(jsonStr, User.class);*/
//    String jsonStr = JSON.toJSONString(user);
//    User1 user1 = JSONObject.parseObject(jsonStr, User1.class);
    String name = student.getName();
    String uuid = UUID.randomUUID().toString();
    String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
        + uuid.substring(19, 23) + uuid.substring(24);
    List<String> idList = userService.selectId(name);
    boolean result = false;
    if (idList == null || idList.size() == 0) {
      result = userService.insert(id, name, "test", 12);
    }
    if (idList.size() != 0) {
      out.print("The account already exists");
      return;
    }
    if (!result) {
      out.print("Add User Fail");
    } else {
      out.println("Add User Success");
    }
    out.close();
  }

  @RequestMapping(value = "/testToken.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
  public void testToken(@RequestBody Student1 student, HttpServletRequest request,
      HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    /*User user = JSONObject.parseObject(jsonStr, User.class);*/
//    String jsonStr = JSON.toJSONString(user);
//    User1 user1 = JSONObject.parseObject(jsonStr, User1.class);
    String name = student.getName();
    String uuid = UUID.randomUUID().toString();
    String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
        + uuid.substring(19, 23) + uuid.substring(24);
    List<String> idList = userService.selectId(name);
    boolean result = false;
    if (idList == null || idList.size() == 0) {
      result = userService.insert(id, name, "test", 12);
    }
    if (idList.size() != 0) {
      out.print("The account already exists");
      return;
    }
    if (!result) {
      out.print("Add User Fail");
    } else {
      out.println("Add User Success");
    }
    out.close();
  }

}
