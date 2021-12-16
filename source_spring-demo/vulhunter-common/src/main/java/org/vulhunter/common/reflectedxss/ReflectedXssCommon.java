package org.vulhunter.common.reflectedxss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class ReflectedXssCommon {

  public void printWriterTestPrintf(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    System.out.println(
        "---------------------++++++++io PrintWriter printf start++++++++--------------------");
    String testString = request.getParameter("testString");
    System.out.println("testString == >"+testString);
    Object o = testString;
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter writer = response.getWriter();

    String[] arr = {"a", "b", "c", "d"};

    StringBuffer stringBuffer = new StringBuffer(testString);
    //O2O java.lang.StringBuffer.deleteCharAt(int)
    stringBuffer.deleteCharAt(0);
    testString = stringBuffer.toString();
    writer.printf(Locale.getDefault(), testString, o, 100, arr);

    writer.flush();
    writer.close();
    System.out.println(
        "---------------------++++++++io PrintWrite printf end++++++++--------------------");
  }

}
