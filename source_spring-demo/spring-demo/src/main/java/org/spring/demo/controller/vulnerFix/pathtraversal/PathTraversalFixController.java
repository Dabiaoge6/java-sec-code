package org.spring.demo.controller.vulnerFix.pathtraversal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.vulhunter.vulnerfix.pathtraversal.PathTraversalFixCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("pathTraversalFix")
public class PathTraversalFixController {

    private Logger logger = Logger.getLogger(PathTraversalFixController.class);

    @RequestMapping(value = "/uploadFix.do", method = RequestMethod.POST)
    public void uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String orFilename = file.getOriginalFilename();
        PrintWriter out = null;
        try {
            out = response.getWriter();
//            boolean isValid = PathTraversalFixCommon.isValidFile(orFilename);
            boolean isValid = PathTraversalFixCommon.isValidFileName(orFilename);
            if(!isValid){
                out.print(orFilename + " is invalid");
                return;
            }
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

    @RequestMapping("/fileInitStringFix.do")
    @ResponseBody
    public String fileInitString(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        String path = request.getParameter("path");
        boolean result = PathTraversalFixCommon.mathch(path);
        if (result) {
            logger.info("this path isn't allow");
            return "this path isn't allow";
        }
        File file = new File(path);
        return file.getPath();
    }

    /**
     * 使用java.io
     *
     * @param request
     * @param response
     * @return
     * @throws URISyntaxException
     */
    // /spring-demo/fileInitUri.do?path=file:///D:gongju
    @RequestMapping("/fileInitUriFix.do")
    @ResponseBody
    public String fileInitUri(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        String path = request.getParameter("path");
        URI url = new URI(path);
        URI url2 = url.normalize();
        File file2 = new File(url2);
        return file2.getPath();
    }

}
