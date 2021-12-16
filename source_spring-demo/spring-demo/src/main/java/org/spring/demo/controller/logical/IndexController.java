package org.spring.demo.controller.logical;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.dao.demo.bean.Role;
import org.dao.demo.bean.User;
import org.dao.demo.dao.UserDao;
import org.spring.demo.util.PhoneCode;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vulhunter.util.RandomValidateCode;

@Controller
public class IndexController {

	@Resource
	private UserDao userDao;
	
	@ResponseBody
    @RequestMapping(value = "/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@ResponseBody
    @RequestMapping(value = "/getNumberVerifyCode")
    public void getNumberVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandNumbercode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@ResponseBody
    @RequestMapping("/userLogin")
    public Map<String,String> loginCheck(@RequestParam("username") String name,@RequestParam("password") String pwd, HttpServletRequest request,
                                   HttpServletResponse response, HttpSession session) throws Exception {
        HashMap<String, String> result = new HashMap<>();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userDao.findUserByName(name);
       // int loginErro = 0;
       /* if(!checkLock(session, name)) {
            result.put("msg","用户已锁定，请联系管理员！或者30分钟后再尝试登录");
            return result;
        }*/
/*        if(user!=null||user.getLoginErro()>3){
            if(user.getLoginErro()>3){
                result.put("msg","用户已锁定，请联系管理员！");
                return result;}else {
            }
        }*/
        if(user!=null){
            //boolean flag = encoder.matches(pwd, user.getPwd());
            if (StringUtils.isNotEmpty(user.getPwd()) && user.getPwd().equals(pwd)){
                result.put("success", "true");
                result.put("msg","登录成功");
                result.put("role", user.getRole());
                cleanFailNum(session, name);
                session.invalidate();
                HttpSession newSession = request.getSession();
                response.setHeader("Set-Cookie", "JSESSIONID=" + newSession.getId());
               /* loginErro = 0;
                user.setLoginErro(loginErro);
                userDao.saveAndFlush(user);*/
            }else {
                addFailNum(session, name);
                /*loginErro++;*/
                /*user.setLoginErro(loginErro);
                userDao.save(user);*/
                result.put("msg","密码错误请检查！");
            }
        }else{
           /* loginErro++;*/
            addFailNum(session, name);
            /*user.setLoginErro(loginErro);
            userDao.save(user);*/
            result.put("msg","用户名不存在请检查！");
        }
        return result;
    }

	@ResponseBody
	@RequestMapping("/pay")
	public Map<String,String> pay(@RequestParam("account") String account,@RequestParam("verification") String verification, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		HashMap<String, String> result = new HashMap<>();
		String verifyCode = (String)session.getAttribute("RANDOMNUMBERVALIDATECODEKEY");
	    if (!verification.equals(verifyCode)) {
	      result.put("message", "验证码填写错误");
	    } else {
	      result.put("message", "支付成功");
	    } 
	    return result;
	}
	
	/**
     * 清理用户登录失败的记录
     * @param session
     * @param username
     */
    public void cleanFailNum(HttpSession session, String username) {
        session.getServletContext().removeAttribute(username);
    }
	
    /**
     * 新增用户登录失败次数
     * @param session
     * @param username
     */
    public void addFailNum(HttpSession session, String username) {
        Object o = session.getServletContext().getAttribute(username);
        HashMap<String,Object> map = null;
        int num= 0;
        if(o==null) {
            map = new HashMap<String,Object>();
        }else {
            map  = (HashMap<String, Object>) o;
            num  = (int) map.get("num");
            Date date = (Date) map.get("lastDate");
            long timeDifference = ((new Date().getTime()-date.getTime())/60/1000);
            if(timeDifference>=30) {
                num=0;
            }
        }
        map.put("num", num+1);
        map.put("lastDate", new Date());
        session.getServletContext().setAttribute(username, map);
    }
    
    @ResponseBody
    @RequestMapping("/getCode")
    public Map<String,String> getCode(@RequestParam("tel")String tel, HttpServletRequest request,
            HttpServletResponse response){
    	HttpSession session = request.getSession();
    	String phoneCode = "";
    	try {
    		phoneCode = PhoneCode.vcode();
            PhoneCode.getPhonemsg(tel,phoneCode);
		} catch (Exception e) {
		}
        System.out.println("验证码"+phoneCode);
        session.setAttribute("phoneCode",phoneCode);
        Map<String,String> map = new HashMap<>();
        map.put("msg","验证码发送成功");
        return map;
    }
    
    @Transactional
    @ResponseBody
    @RequestMapping("/registerUser")
    public Map<String,String> register(User user,@RequestParam("code")String code, HttpServletRequest request,
            HttpServletResponse response, HttpSession session){
    	Map<String,String> msg = new HashMap();
    	String phonemsg = (String)session.getAttribute("phoneCode");
        User existUser = userDao.findUserByName(user.getName());
        if (existUser != null) {
        	msg.put("message",existUser.getName() + "用户已存在！");
        } else  if(StringUtils.isEmpty(phonemsg) || !phonemsg.equals(code)){
            msg.put("message","短信验证码错误");
        } else {
        	user.setPwd(user.getPwd());
            user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            userDao.save(user);
            Role role = new Role();
            role.setName(user.getRole());
            role.setUsername(user.getName());
            //roleDao.save(role);
            msg.put("message","注册成功");
        }
        return msg;
    }
    
    public String toMd5Str(String data) {
    	MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        String result = new String(Hex.encode(digest.digest(data.getBytes())));
        if (StringUtils.isNotEmpty(result)) {
        	data = result.toUpperCase();
        }
        return data;
    }
    
}
