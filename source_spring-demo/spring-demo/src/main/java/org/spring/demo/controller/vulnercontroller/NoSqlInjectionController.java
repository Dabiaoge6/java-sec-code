package org.spring.demo.controller.vulnercontroller;

import com.mongodb.CommandResult;
import com.mongodb.DB;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.demo.util.MongDBUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hujj on 2020-07-10.
 */
@Controller
@RequestMapping("nosqlInjection")
public class NoSqlInjectionController {

    protected static DB mongodb = null;

    static {
        mongodb = MongDBUtil.getConnect();
    }

    private void end(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("NosqlInjection");
        out.close();
    }

    @RequestMapping("/nosql001.do")
    public void doEvalNoSql(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String eval = request.getParameter("eval");
        CommandResult commandResult = null;
        try {
            commandResult = mongodb.doEval(eval);
        } catch (Exception e) {
            end(response);
        }
        //responese.getWriter().println(commandResult.getString("retval"));
        end(response);
    }

}
