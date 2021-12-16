package org.spring.demo.controller.vulnerFix.sqlinjection;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.sqlinjection.SqlInjectionCommon;
import org.vulhunter.vulnerfix.sqlinjection.SqlInjectionFixCommon;

@Controller
@RequestMapping("sqlInjectionFix")
public class SqlInjectionFixController {

	@RequestMapping("/statementAddBatchFix.do")
	public void preparedstatementAddBatch(HttpServletRequest request, HttpServletResponse responese)
			throws IOException {
		SqlInjectionFixCommon sqlInjectionTestService = new SqlInjectionFixCommon();
		sqlInjectionTestService.preparedstatementAddBatch(request);
		PrintWriter out = responese.getWriter();
		out.println("test Statement.addBatch");
	}

	@RequestMapping("/statementExecuteFix.do")
	public void statementExecute(HttpServletRequest request, HttpServletResponse responese) throws IOException {
		SqlInjectionFixCommon sqlInjectionTestService = new SqlInjectionFixCommon();
		sqlInjectionTestService.statementExecute(request);
		PrintWriter out = responese.getWriter();
		out.println("test Statement.execute(sql)");
	}
	
	@RequestMapping("/statementExecute-intArrFix.do")
    public void statementExecuteIntArrFix(HttpServletRequest request,HttpServletResponse responese) throws IOException {
		SqlInjectionFixCommon sqlInjectionTestService = new SqlInjectionFixCommon();
        sqlInjectionTestService.statementExecuteIntArr(request);
        PrintWriter out = responese.getWriter();
		out.println("test Statement.execute(sql,intArr)");
    }

}
