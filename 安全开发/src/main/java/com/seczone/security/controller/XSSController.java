package com.seczone.security.controller;

import com.seczone.security.dao.Comment;
import com.seczone.security.dao.UserVo;
import com.seczone.security.mapper.CommentMapper;
import com.seczone.security.util.ServerResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/xss")
public class XSSController {
    @Autowired
    CommentMapper commentMapper;


    /**
     *
     * 反射型xss
     * http://localhost:8080/xss/reflect?xss=<script>alert(1)</script>
     *
     * @param xss unescape string
     */

    @RequestMapping("/reflectindex")

    public  String reflectindex(String xss)
    {
        return "reflectXss";
    }

    @RequestMapping("/reflect")
    public static String reflect(String xss,Model model)
    {
        System.out.println(xss);
        model.addAttribute("xsspara",xss);
        return "reflectXss";
    }



    /**
     *
     * 存储型xss
     * http://localhost:8080/xss/storage
     *
     * @param
     */
    @GetMapping("/storage")
    public String StorageXss(Model model){
        //1、读取所有评论
        final List<Comment> comments = commentMapper.SearchAllComment();
        model.addAttribute("comments",comments);

        return "storagexss";
    }

    @ResponseBody
    @PostMapping("/stroagexss_interface")
    public ServerResponse stroagexssInterface(){
        List<Comment> comments = commentMapper.SearchAllComment();
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setData(comments);
        return  serverResponse;
    }

    @PostMapping("/inseart")
    public String inseartStorageXss(String comment, Model model){
        final UserVo userVo = (UserVo) SecurityUtils.getSubject().getPrincipal();
        //1、插入评论
        System.out.println(userVo.toString());
        System.out.println(comment);
        Comment comment1 = new Comment();
        comment1.setCommentContent(comment);
        comment1.setFromUser(userVo.getUid());
        comment1.setUserName(userVo.getUserName());
        final int i = commentMapper.InsertComment(comment1);
        System.out.println("插入状态为："+i);
        //2、读取所有评论
        final List<Comment> comments = commentMapper.SearchAllComment();
        model.addAttribute("comments",comments);
        return "storagexss";
    }

    /**
     * <a href='' onclick=alert('xss')>type</a>
     * dom型xss注入
     * 输入 <svg/onload=alert(1)>
     *     <a href='' "alert('hello world!')">'>what do you see?</a>
     *     <a href='' "alert('hello world!')">what do you see?</a>
     *     <input type=”text ”  onfocus=prompt(1) autofocus>   // onfocus： 当 input 输入框获取焦点时执行一段 Javascript代码：
     */
    @GetMapping("/domxss")
    public String domxss(Model model){
        model.addAttribute("a","<prop>alert(/xss/)</prop>");

        return "domxss";
    }

    /**
     * 根据XSS窃取Cookie
     */




}
