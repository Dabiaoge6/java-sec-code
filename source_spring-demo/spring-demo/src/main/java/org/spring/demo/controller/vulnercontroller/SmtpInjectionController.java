package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Properties;

@Controller
@RequestMapping("smtpInjection")
public class SmtpInjectionController {

  @RequestMapping("/send.do")
  public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String from = request.getParameter("from");
    String recipients = request.getParameter("recipients");
    String password = request.getParameter("password");
    String subject = request.getParameter("subject");
    String content = request.getParameter("content");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    PrintWriter writer = response.getWriter();
    if (from == null || from.length() == 0) {
      writer.println("发件人不能为空");
      writer.close();
      return;
    }
    if (recipients == null || recipients.length() == 0) {
      writer.println("收件人不能为空");
      writer.close();
      return;
    }

    Properties properties = new Properties();
    properties.put("mail.transport.protocol", "smtp");// 连接协议
    properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
    properties.put("mail.smtp.port", 465);// 端口号
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
    properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
    // 得到回话对象
    Session session = Session.getInstance(properties);
    // 获取邮件对象
    Message message = new MimeMessage(session);
    // 设置发件人邮箱地址
    message.setFrom(new InternetAddress(from));
    // 设置收件人邮箱地址
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
    // 设置邮件标题
    message.setSubject(subject);
    // 设置邮件内容
    message.setText(content);
    // 得到邮差对象
    Transport transport = session.getTransport();
    // 连接自己的邮箱账户
    try{
      transport.connect(from, password);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
      // 发送邮件
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      writer.println("发送邮件成功");
    }catch (Exception e) {
      writer.println("上报漏洞：1.SMTP头注入 2.不安全的哈希算法");
    }
  }
}
