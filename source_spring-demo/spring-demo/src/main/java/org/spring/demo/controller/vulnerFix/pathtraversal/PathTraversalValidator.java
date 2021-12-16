package org.spring.demo.controller.vulnerFix.pathtraversal;

import org.hibernate.validator.constraints.NotBlank;
import org.spring.demo.controller.vulnercontroller.validate.InputValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

@Controller
@RequestMapping("pathTraversalValidator")
@Validated
public class PathTraversalValidator {

    @RequestMapping(value = "/uploadValidator.do", method = RequestMethod.POST)
        public void uploadFile(@RequestParam @NotBlank MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String orFilename = file.getOriginalFilename();
        PrintWriter out = null;
        try {
            out = response.getWriter();
//            boolean isValid = PathTraversalFixCommon.isValidFile(orFilename);
         /*   boolean isValid = PathTraversalFixCommon.isValidFileName(orFilename);
            if(!isValid){
                out.print(orFilename + " is invalid");
                return;
            }*/
            String path = request.getSession().getServletContext().getRealPath("") + "\\static\\upload";
            File dir = new File(path);
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdir();
            }
            file.transferTo(new File(path + "\\" + orFilename));
            out.print(orFilename + " upload success!");
            out.flush();
            out.close();
        } catch (IllegalStateException e) {
            out.print(orFilename + " upload fail!");
            e.printStackTrace();
        } catch (IOException e) {
            out.print(orFilename + " upload fail!");
            e.printStackTrace();
        }
    }

    @RequestMapping("/fileInitStringValidator.do")
    @ResponseBody
    public String fileInitString(@RequestParam @InputValidator String path, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        /*boolean result = PathTraversalFixCommon.mathch(path);
        if (result) {
            return "this path isn't allow";
        }*/
        File file = new File(path);
        return file.getPath();
    }
}
