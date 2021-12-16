package org.spring.demo.controller.vulnerFix.csrf;

import org.apache.log4j.Logger;
import org.service.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("csrfFix")
public class CsrfFixController {

    private Logger logger = Logger.getLogger(CsrfFixController.class);

    @Autowired
    private UserService userService;

    private boolean isEquals(String hiddenToken, String csrfToken) {
        if (hiddenToken == null || hiddenToken.equals("")) {
            logger.info("form-hidden submit null token");
            return false;
        }
        if (csrfToken == null || csrfToken.equals("")) {
            logger.info("session get null csrfToken");
            return false;
        }
        if (csrfToken.equals(hiddenToken)) {
            logger.info("CSRF PROTECTED");
            return true;
        } else {
            logger.info("CSRF");
            return false;
        }
    }

    private File getFile(String path) {
        String fileName = path + "\\" + "csrf.txt";
        File csrfFile = new File(fileName);
        if (!csrfFile.exists()) {
            try {
                csrfFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csrfFile;
    }

    @RequestMapping("/csrfToken.do")
    public void csrfToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断session是否相等
        String hiddenToken = request.getParameter("protectedKey");
        HttpSession session = request.getSession();
        String csrfToken = session.getAttribute("protectedKey").toString();
        boolean isEqual = isEquals(hiddenToken, csrfToken);
        if (!isEqual) {
            return;
        }
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        int age = new Integer(request.getParameter("age"));
        String uuid = UUID.randomUUID().toString();
        String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
                + uuid.substring(19, 23) + uuid.substring(24);
        List<String> idList = userService.selectId(name);
        if(idList.size() != 0){
            out.print("The account already exists");
            return;
        }
        boolean result = userService.insert(id, name, pwd, age);
        if(!result){
            out.print("Add User Fail");
        }else{
            out.println("Add User Success");
        }
        out.close();
    }

    /**
     * spring-security 保护
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/csrfProtected.do")
    public void csrfProtected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断session是否相等
        String hiddenToken = request.getParameter("_csrf");
//        HttpSession session = request.getSession();
//        String csrfToken = session.getAttribute("csrfToken").toString();
        /*boolean isEqual = isEquals(hiddenToken, csrfToken);
        if (!isEqual) {
            return;
        }*/
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        int age = new Integer(request.getParameter("age"));
        String uuid = UUID.randomUUID().toString();
        String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
                + uuid.substring(19, 23) + uuid.substring(24);
        List<String> idList = userService.selectId(name);
        if(idList.size() != 0){
            out.print("The account already exists");
            return;
        }
        boolean result = userService.insert(id, name, pwd, age);
        if(!result){
            out.print("Add User Fail");
        }else{
            out.println("Add User Success");
        }
        out.close();
    }
}
