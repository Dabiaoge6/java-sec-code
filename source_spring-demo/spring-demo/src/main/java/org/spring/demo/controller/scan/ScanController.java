package org.spring.demo.controller.scan;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.grizzly.http.server.Response;
import org.jaxen.dom.DOMXPath;
import org.service.demo.service.UserService;
import org.spring.demo.controller.scan.bean.ReceiveContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import play.mvc.Results;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.lang.IllegalStateException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping("scanIssue")
public class ScanController {

	// mq通讯地址
	private static String url = "tcp://10.0.1.183:61616";

	// 队列名称
	private static String queueName = "requestQueueName";

	private static String bugQueueName = "newBugQueueName";

	@Resource
	private UserService userService;

	@RequestMapping(value = "/unencryptedPasswordForm.do")
	public void testUnencryptedPasswordForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("test:");
	}

	@RequestMapping(value = "/responseSplitting.do")
	public void testResponseSplitting(HttpServletRequest req, HttpServletResponse res) {
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		System.out.println(value);
		if (StringUtils.isNotBlank(key)) {
			String[] headers = null;
			if (value.contains("\r\n")) {
				headers = value.split("\r\n");
				if (headers.length > 1) {
					for (int i = 1; i < headers.length; i++) {
						if (headers[i].contains(":")) {
							String[] split = headers[i].split(":");
							if (split.length > 1) {
								res.setHeader(split[0], split[1]);
							}
						}
					}
				}
			} else if(value.contains("%0d%0a")) {
				headers = value.split("%0d%0a");
				if (headers.length > 1) {
					for (int i = 1; i < headers.length; i++) {
						if (headers[i].contains(":")) {
							String[] split = headers[i].split(":");
							if (split.length > 1) {
								res.setHeader(split[0], split[1]);
							}
						}
					}
				}
			} else {
				res.setHeader(key, value);
			}
		}
	}

	@RequestMapping(value = "/pathTraversal1.do", method = RequestMethod.POST)
	public void testPathTraversal1(@RequestParam MultipartFile file, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String orFilename = file.getOriginalFilename();
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String path = req.getSession().getServletContext().getRealPath("") + "\\static\\upload";
			System.out.println("test path:" + path);
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

	@RequestMapping(value = "/getPath.do", method = RequestMethod.POST)
	public void getPath(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String path = req.getParameter("path");
		System.out.println("scan path:" + path);
		if (path.contains("arachni") || path.contains("file")) {
			path = "C://Users//sz//Desktop//test.json";
		}
		File file = new File(path);
		String orFilename = file.getName();
		System.out.println("file name:" + orFilename);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			String pathUrl = req.getSession().getServletContext().getRealPath("") + "\\static\\upload";
			File dir = new File(pathUrl);
			if (!dir.exists() && !dir.isDirectory()) {
				dir.mkdir();
			}
			new File(pathUrl + "\\" + orFilename);
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

	@RequestMapping(value = "/xomFile.do")
	public void testXomFile(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Document doc = null;
		String xmlName = req.getParameter("xmlName");
		String path = req.getSession().getServletContext().getRealPath("") + "static" + File.separator + "xml";
		String filename = path + File.separator + xmlName;
		PrintWriter out = null;
		try {
			out = res.getWriter();
			doc = new Builder().build(new File(filename));
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String testContent = doc.getRootElement().getValue();
		out.println(testContent);
	}

	@RequestMapping(value = "/xxeIssue.do", method = RequestMethod.POST)
	public void xxeIssue(@RequestParam String xmlString, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		System.out.println("------>:" + xmlString);
	}

	/**
	 * Path Traversal
	 */
	@RequestMapping(value = "/pathTraversal.do", method = RequestMethod.POST)
	public void pathTraversal(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String path = req.getParameter("path");
		System.out.println("scan path:" + path);
		File file = new File(path);
		int len = 0;
		byte[] buffer = new byte[1024 * 4];
		InputStream input = new FileInputStream(file);
		while ((len = input.read(buffer)) != -1) {
			res.getOutputStream().write(buffer, 0, len);
		}
		res.getOutputStream().flush();
		input.close();
	}

	/**
	 * Path Traversal
	 */
	@RequestMapping(value = "/pathTraversalGet.do", method = RequestMethod.GET)
	public void pathTraversalGet(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "path", required = true) String path) throws IOException {
		System.out.println("scan path:" + path);
		File file = new File(path);
		int len = 0;
		byte[] buffer = new byte[1024 * 4];
		InputStream input = new FileInputStream(file);
		while ((len = input.read(buffer)) != -1) {
			res.getOutputStream().write(buffer, 0, len);
		}
		res.getOutputStream().flush();
		input.close();
	}
	
	@RequestMapping(value = "/jsonPathTraversal.do", method = RequestMethod.POST)
	public void jsonPathTraversal(HttpServletRequest req, HttpServletResponse res,
			@RequestBody ReceiveContent receiveContent) throws IOException {
		System.out.println("scan path:" + receiveContent.getPath());
		File file = new File(receiveContent.getPath());
		int len = 0;
		byte[] buffer = new byte[1024 * 4];
		InputStream input = new FileInputStream(file);
		while ((len = input.read(buffer)) != -1) {
			res.getOutputStream().write(buffer, 0, len);
		}
		res.getOutputStream().flush();
		input.close();
	}
	
	/**
	 *	获取文件夹下所有文件名称
	 */
	@RequestMapping(value = "/commonDirectoriesGet.do", method = RequestMethod.GET)
	public void commonDirectories(HttpServletRequest req, HttpServletResponse res, 
			@RequestParam(value = "path", required = true) String path) throws IOException {
		PrintWriter out = res.getWriter();
		String filePath = req.getSession().getServletContext().getRealPath("") + path;
		List<String> fileName = new ArrayList<String>();
		fileName = getAllFileName(filePath, fileName);;
		for (String string : fileName) {
			out.println(string);
		}
		out.close();
	}
	
	public static List<String> getAllFileName(String path,List<String> fileName) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
              System.out.println("文     件：" + tempList[i]);
                fileName.add(tempList[i].getName());
            }
            if (tempList[i].isDirectory()) {
              System.out.println("文件夹：" + tempList[i]);
                getAllFileName(tempList[i].getAbsolutePath(),fileName);
            }
        }
        return fileName;
    }
	
	
	/**
	 * Path Traversal
	 */
	@RequestMapping(value = "/fileInclusion.do", method = RequestMethod.POST)
	public void fileInclusion(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String path = req.getParameter("path");
		System.out.println("scan file inclusion path:" + path);
		File file = new File(path);
		int len = 0;
		byte[] buffer = new byte[1024 * 4];
		InputStream input = new FileInputStream(file);
		while ((len = input.read(buffer)) != -1) {
			res.getOutputStream().write(buffer, 0, len);
		}
		res.getOutputStream().flush();
		input.close();
	}

	@RequestMapping(value = "/fileInclusionGet.do", method = RequestMethod.GET)
	public void fileInclusionGet(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "path", required = true) String path) throws IOException {
		System.out.println("scan file inclusion path:" + path);
		File file = new File(path);
		int len = 0;
		byte[] buffer = new byte[1024 * 4];
		InputStream input = new FileInputStream(file);
		while ((len = input.read(buffer)) != -1) {
			res.getOutputStream().write(buffer, 0, len);
		}
		res.getOutputStream().flush();
		input.close();
	}
	
	@RequestMapping(value = "/jsonFileInclusion.do", method = RequestMethod.POST)
	public void jsonFileInclusion(HttpServletRequest req, HttpServletResponse res,
			@RequestBody ReceiveContent receiveContent) throws IOException {
		System.out.println("scan file inclusion path:" + receiveContent.getPath());
		File file = new File(receiveContent.getPath());
		int len = 0;
		byte[] buffer = new byte[1024 * 4];
		InputStream input = new FileInputStream(file);
		while ((len = input.read(buffer)) != -1) {
			res.getOutputStream().write(buffer, 0, len);
		}
		res.getOutputStream().flush();
		input.close();
	}
	
	/**
	 * sql注入
	 */
	@RequestMapping("/sqlInjectionIssue.do")
	public void sqlInjectionIssue(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		int age = new Integer(request.getParameter("age"));
		String uuid = UUID.randomUUID().toString();
		String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
		name = "'" + name + "'";
		pwd = "'" + pwd + "'";
		id = "'" + id + "'";
		List<String> idList = userService.selectId(name);
		boolean result = false;
		if (idList == null || idList.size() == 0) {
			result = userService.insert(id, name, pwd, age);
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
	
	@RequestMapping("/sqlJsonInjectionIssue.do")
	public void sqlJsonInjectionIssue(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ReceiveContent receiveContent) throws IOException {
		PrintWriter out = response.getWriter();
		String name = receiveContent.getName();
		String pwd = receiveContent.getPwd();
		int age = receiveContent.getAge();
		String uuid = UUID.randomUUID().toString();
		String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
		name = "'" + name + "'";
        pwd = "'" + pwd + "'";
        id = "'" + id + "'";
		List<String> idList = userService.selectId(name);
		boolean result = false;
		if (idList == null || idList.size() == 0) {
			result = userService.insert(id, name, pwd, age);
		}
		List<String> nameList = userService.selectName();
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

	@RequestMapping("/sqlInjectionGetIssue.do")
	public void sqlInjectionGetIssue(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "name", required = true) String name) throws IOException {
		PrintWriter out = response.getWriter();
		name = "'" + name + "'";
		List<String> idList = userService.selectId(name);
		for (String string : idList) {
			out.write(string);
		}
		out.close();
	}

	/**
	 * cmd injection Operating system command injection (timing attack)
	 */
	@RequestMapping("/cmdIssue.do")
	public void cmdInjection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cmd = request.getParameter("cmd");
		System.out.println("=========cmd:=========" + cmd + "================");
		Process pro = Runtime.getRuntime().exec(cmd);
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		String msg = null;
		while ((msg = bufReader.readLine()) != null) {
			System.out.println(msg + "<----msg");
		}
		end(response);
	}

	/**
	 * cmd injection Operating system command injection
	 */
	@RequestMapping("/cmdInjectionIssue.do")
	public void unixCmdInjection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cmd = request.getParameter("cmd");
		try {
			Process pro = Runtime.getRuntime().exec(cmd);
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String msg = null;
			PrintWriter out = response.getWriter();
			while ((msg = bufReader.readLine()) != null) {
				out.println(msg);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/cmdInjectionGetIssue.do")
	public void cmdInjectionGetIssue(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cmdGet", required = true) String cmdGet) throws Exception {
		try {
			Process pro = Runtime.getRuntime().exec(cmdGet);
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String msg = null;
			PrintWriter out = response.getWriter();
			while ((msg = bufReader.readLine()) != null) {
				out.println(msg);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/cmdJsonInjectionIssue.do")
	public void unixCmdJsonInjection(HttpServletRequest request, HttpServletResponse response, @RequestBody ReceiveContent receiveContent) throws Exception {
		String cmd = receiveContent.getName();
		try {
			Process pro = Runtime.getRuntime().exec(cmd);
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String msg = null;
			PrintWriter out = response.getWriter();
			while ((msg = bufReader.readLine()) != null) {
				out.write(msg);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/cmdAsynIssue.do")
	public void cmdAsynIssue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cmd = request.getParameter("cmd");
		try {
			System.out.println("------------->cmdAsynIssue");
			execCMD1(cmd, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		end(response);
	}

	@RequestMapping("/xrapScan.do")
	public void xrapScan(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cmd = request.getParameter("path");
		try {
			execCMD1(cmd, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		end(response);
	}

	@RequestMapping("/xrapScanResult.do")
	public void xrapScanResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		String content = "";
		String fileName = request.getParameter("fileName");
		String path = request.getSession().getServletContext().getRealPath("/") + "../" + fileName;
		System.out.println("get xrap scan result file path: " + path);
		try {
			File file = new File(path);
			if (file.exists()) {
				content = FileUtils.readFileToString(file, "UTF-8");
			} else {
				System.out.println("file not exist!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(content);
		out.close();
	}
	

	private HttpSession getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String execCMD(String path, int type) throws IOException {
		Process pro = Runtime.getRuntime().exec(path);
		BufferedInputStream br = new BufferedInputStream(pro.getInputStream());
		BufferedInputStream br1 = new BufferedInputStream(pro.getErrorStream());
		int ch;
		StringBuffer text = new StringBuffer();
		while ((ch = br.read()) != -1) {
			text.append((char) ch);
		}
		StringBuffer text1 = new StringBuffer();
		while ((ch = br1.read()) != -1) {
			text1.append((char) ch);
		}
		return text.length() > 9 ? text.toString() : text1.toString();
	}

	private void end(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("CmdInjection");
		out.close();
	}

	/**
	 * XPath Injection
	 */
	@RequestMapping("/xpathIjection1.do")
	public void xpathIjection1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String xpathUrl = request.getParameter("xpath");
		String xml = "<resp><status>good</status><msg>hi</msg></resp>";
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		InputSource source = new InputSource(new StringReader(xml));
		String status = "";
		try {
			status = (String) xpath.evaluate(xpathUrl, source);
			PrintWriter out = response.getWriter();
			out.println(status);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * xpath issues
	 */
	@RequestMapping("/xpathIjectio2.do")
	public void xpathIjection2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String xmlFile = this.getClass().getClassLoader().getResource("score/xmlPath.xml").getPath();
		XPath xPath = XPathFactory.newInstance().newXPath();
		InputSource inputXml = new InputSource(xmlFile);
		String query = "//users/user[name/text()='" + username + "' and pass/text()='" + password + "']"
				+ "/secret/text()";
		String secret = (String) xPath.evaluate(query, inputXml, XPathConstants.STRING);
		PrintWriter out = response.getWriter();
		out.println(secret);
		out.close();
	}

	@RequestMapping("/xpathIjection.do")
	public void xpathIjection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xmlFile = this.getClass().getClassLoader().getResource("score/xmlPath2.xml").getPath();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.parse(new InputSource(xmlFile));
		String eID = request.getParameter("employeeID");
		if (eID == null) {
			eID = "";
		}
		String xpathExpr = "/employees/employee[@id='" + eID + "']";
		org.jaxen.XPath expression = new DOMXPath(xpathExpr);
		List nodes = expression.selectNodes(doc);
		for (int i = 0; i < nodes.size(); i++) {
			Element employee = (Element) nodes.get(i);
			response.getWriter().println(employee.getAttribute("lastname") + " " + employee.getAttribute("firstname")
					+ " : " + employee.getAttribute("annualsalary"));
		}
	}

	@RequestMapping("/xpathGetIjection.do")
	public void xpathGetIjection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xmlFile = this.getClass().getClassLoader().getResource("score/xmlPath2.xml").getPath();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.parse(new InputSource(xmlFile));
		String eID = request.getParameter("employeeID");
		if (eID == null) {
			eID = "";
		}
		String xpathExpr = "/employees/employee[@id='" + eID + "']";
		org.jaxen.XPath expression = new DOMXPath(xpathExpr);
		List nodes = expression.selectNodes(doc);
		for (int i = 0; i < nodes.size(); i++) {
			Element employee = (Element) nodes.get(i);
			response.getWriter().println(employee.getAttribute("lastname") + " " + employee.getAttribute("firstname")
			+ " : " + employee.getAttribute("annualsalary"));
		}
	}

	@RequestMapping("/getUser.do")
	public String getAll(Model model) {
		List<String> nameList = userService.selectName();
		model.addAttribute("userList", nameList);
		return "/csrf/csrf";
	}

	@RequestMapping("/unvalidatedRedirectTest.do")
	public void unvalidatedRedirectTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String location = request.getParameter("location");
		System.out.println("location:" + location);
		Results.redirect(location);
		PrintWriter out = response.getWriter();
		out.println("UnValidated Redirect");
	}

	@RequestMapping("/unvalidatedRedirect.do")
	public void unvalidatedRedirect(HttpServletRequest request, HttpServletResponse response) {
		Response resp = Response.create();
		String location = request.getParameter("location");
		Locale deflocale = Locale.getDefault();
		String locationLow = "";
		for (int i = 0; i < 10000; i++) {
			locationLow = location.toLowerCase(deflocale);
		}
		try {
			resp.sendRedirect(locationLow);
			PrintWriter out = response.getWriter();
			out.println("UnValidated Redirect");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/unvalidatedRedirect1.do")
	public void unvalidatedRedirect1(HttpServletRequest request, HttpServletResponse response)
			throws URISyntaxException, IOException {
		String uriStr = request.getParameter("testuri");
		URI uri = new URI(uriStr);
		System.out.println("testUri:" + uriStr);
		javax.ws.rs.core.Response.temporaryRedirect(uri);
		PrintWriter out = response.getWriter();
		out.println("UnValidated Redirect");
	}

	@RequestMapping("/unvalidatedRedirect2.do")
	public void unvalidatedRedirect2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String location = request.getParameter("location");
			StringWriter sw = new StringWriter();
			/*
			 * P2O
			 */
			sw.write(location);
			/*
			 * O2R
			 */
			response.sendRedirect("../" + sw.toString());
			PrintWriter out = response.getWriter();
			out.println("UnValidated Redirect");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * xss Cross-Site Scripting (XSS) in HTML tag
	 */
	@RequestMapping("/xssIssue.do")
	public String xssIssue(HttpServletRequest request, HttpServletResponse response) {
		String[] messages = request.getParameterValues("message");
		String message = messages[0];
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		return "/scan/xss/xssIssue";
	}

	public void execCMD1(String path, int type) throws Exception {
		String isc = "";
		String isShell = "";
		if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
			isShell = "cmd";
			isc = "/c";
		} else {
			isShell = "sh";
			isc = "-c";
		}
		String[] cmd = new String[] { isShell, isc, path };
		Process pro = Runtime.getRuntime().exec(cmd);
		BufferedInputStream br = new BufferedInputStream(pro.getInputStream());
		BufferedInputStream br1 = new BufferedInputStream(pro.getErrorStream());
		int ch;
		StringBuffer text = new StringBuffer();
		while ((ch = br.read()) != -1) {
			text.append((char) ch);
		}
		StringBuffer text1 = new StringBuffer();
		while ((ch = br1.read()) != -1) {
			text1.append((char) ch);
		}
		String end = text.length() > 9 ? text.toString() : text1.toString();
		System.out.println("cmd injection result:" + end);
	}

	@RequestMapping("/sendFileInclusionRequestTest.do")
	public void sendFileInclusionRequestTest(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 创建连接工厂
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
			// 创建连接
			Connection connection = factory.createConnection();
			// 启动连接
			connection.start();
			// 创建会话，参数1，设置是否需要事务方式提交，参数2，消息方式 默认采用自动接收
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建目标(队列)
			Queue queue = session.createQueue(queueName);
			// 创建生产者
			MessageProducer producer = session.createProducer(queue);
			List<String> requestJsonStr = new ArrayList<String>();
			String commonFilesIssueStr = "{\"aftertest\":0,\"available\":true,\"caseId\":\"fa1bd1c78ca84e1da5955af509654f79\",\"contrastOriginalRequest\":false,\"createtime\":1616124199297,\"hash\":\"7a25121a0af0fe055093df1f7c2b4911f8d97f1c588e926e72e66f5e6a60f297\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=10D8382111DBDA89B38BDE3AF6B460A9\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Host\\\":\\\"127.0.0.1\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"23\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"lastupdate\":0,\"method\":\"GET\",\"originalResponseStatus\":0,\"path\":\"/scanIssue/fileInclusionGet.do\",\"pluginid\":0,\"port\":-1,\"protocol\":\"http\",\"query\":\"path=%2Fetc%2Fpasswd\",\"responseid\":0,\"sentAsync\":false,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183/scanIssue/fileInclusionGet.do\",\"userid\":0,\"webdavMethod\":false}";
			requestJsonStr.add(commonFilesIssueStr);
			for (String jsonStr : requestJsonStr) {
				// 创建消息
				TextMessage textMessage = session.createTextMessage(jsonStr);
				// 发送消息
				producer.send(textMessage);
			}
			connection.close();
			PrintWriter out = response.getWriter();
			out.println("Message sent to queue successfully");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/sendCommonFilesIssueRequestTest.do")
	public void sendCommonFilesIssueRequestTest(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 创建连接工厂
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
			// 创建连接
			Connection connection = factory.createConnection();
			// 启动连接
			connection.start();
			// 创建会话，参数1，设置是否需要事务方式提交，参数2，消息方式 默认采用自动接收
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建目标(队列)
			Queue queue = session.createQueue(queueName);
			// 创建生产者
			MessageProducer producer = session.createProducer(queue);
			List<String> requestJsonStr = new ArrayList<String>();
			String commonFilesIssueStr = "{\"aftertest\":0,\"available\":true,\"caseId\":\"a4198d4b4ad4452381cd5dd13f4fd07c\",\"contrastOriginalRequest\":false,\"createtime\":1616122701849,\"hash\":\"995abda3f2c14934760ed4dcb51a0fd7391cf6a166fe81c2f4be077626ffd849\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=10D8382111DBDA89B38BDE3AF6B460A9\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Host\\\":\\\"127.0.0.1\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"23\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"lastupdate\":0,\"method\":\"GET\",\"originalResponseStatus\":0,\"path\":\"\",\"pluginid\":0,\"port\":-1,\"protocol\":\"http\",\"responseid\":0,\"sentAsync\":false,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183\",\"userid\":0,\"webdavMethod\":false}";
			requestJsonStr.add(commonFilesIssueStr);

			for (String jsonStr : requestJsonStr) {
				// 创建消息
				TextMessage textMessage = session.createTextMessage(jsonStr);
				// 发送消息
				producer.send(textMessage);
			}
			connection.close();
			PrintWriter out = response.getWriter();
			out.println("Message sent to queue successfully");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sendCommonDirectoriesRequestTest.do")
	public void sendCommonDirectoriesRequestTest(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 创建连接工厂
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
			// 创建连接
			Connection connection = factory.createConnection();
			// 启动连接
			connection.start();
			// 创建会话，参数1，设置是否需要事务方式提交，参数2，消息方式 默认采用自动接收
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建目标(队列)
			Queue queue = session.createQueue(queueName);
			// 创建生产者
			MessageProducer producer = session.createProducer(queue);
			List<String> requestJsonStr = new ArrayList<String>();
			String commonDirectories = "{\"aftertest\":0,\"available\":true,\"caseId\":\"e04b3b34af924ae1b1703387a356554d\",\"contrastOriginalRequest\":false,\"createtime\":1616122906010,\"hash\":\"042a3f2f3c8e4124f32887aeef574da7249c92aeb4a6bdefc2eae4177997e3de\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=10D8382111DBDA89B38BDE3AF6B460A9\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Host\\\":\\\"127.0.0.1\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"23\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"lastupdate\":0,\"method\":\"GET\",\"originalResponseStatus\":0,\"path\":\"/scanIssue/commonDirectoriesGet.do\",\"pluginid\":0,\"port\":-1,\"protocol\":\"http\",\"query\":\"path=test\",\"responseid\":0,\"sentAsync\":false,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183/scanIssue/commonDirectoriesGet.do\",\"userid\":0,\"webdavMethod\":false}";
			requestJsonStr.add(commonDirectories);
			for (String jsonStr : requestJsonStr) {
				// 创建消息
				TextMessage textMessage = session.createTextMessage(jsonStr);
				// 发送消息
				producer.send(textMessage);
			}
			connection.close();
			PrintWriter out = response.getWriter();
			out.println("Message sent to queue successfully");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/sendRequestTest.do")
	public void sendRequestTest(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 创建连接工厂
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
			// 创建连接
			Connection connection = factory.createConnection();
			// 启动连接
			connection.start();
			// 创建会话，参数1，设置是否需要事务方式提交，参数2，消息方式 默认采用自动接收
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建目标(队列)
			Queue queue = session.createQueue(queueName);
			// 创建生产者
			MessageProducer producer = session.createProducer(queue);
			List<String> requestJsonStr = new ArrayList<String>();
			requestJsonStr.add("{\"aftertest\":0,\"available\":true,\"contrastOriginalRequest\":false,\"createtime\":1610689464698,\"hash\":\"a364389e16a6d57d0d8f4fb422bdcc66b1826b7c8a76aab5000f04ffe4c81b4a\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=4A977DA68C48000A11CCA1EB4F002B59\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scan/xpath/xPathIssue.jsp\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"16\\\",\\\"Content-Type\\\":\\\"application/x-www-form-urlencoded\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"injectionID\":0,\"lastupdate\":0,\"method\":\"POST\",\"originalResponseStatus\":0,\"path\":\"/scanIssue/xpathIjection.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"employeeID=AS789\",\"responseid\":0,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183:80/scanIssue/xpathIjection.do\",\"userid\":0}");
			requestJsonStr.add("{\"aftertest\":0,\"available\":true,\"contrastOriginalRequest\":false,\"createtime\":1610689986684,\"hash\":\"119faaadd96682475d5c9e06cb434169bca7368217efa5d6121208ee196d69da\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=4A977DA68C48000A11CCA1EB4F002B59\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scan/cmd/cmdInjectionAsynIssue.jsp\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"23\\\",\\\"Content-Type\\\":\\\"application/x-www-form-urlencoded\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"injectionID\":0,\"lastupdate\":0,\"method\":\"POST\",\"originalResponseStatus\":0,\"path\":\"/scanIssue/cmdAsynIssue.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"cmd=cat+%2Fetc%2Fpasswd\",\"responseid\":0,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183:80/scanIssue/cmdAsynIssue.do\",\"userid\":0}");
			requestJsonStr.add("{\"aftertest\":0,\"available\":true,\"contrastOriginalRequest\":false,\"createtime\":1610690628789,\"hash\":\"be8fcf86ed01607162f1c7f2c21919d76de5af5e3acce65f09da4e6754dadaf3\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=4A977DA68C48000A11CCA1EB4F002B59\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scan/cmd/unixCmdInjectionIssue.jsp\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"23\\\",\\\"Content-Type\\\":\\\"application/x-www-form-urlencoded\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"injectionID\":0,\"lastupdate\":0,\"method\":\"POST\",\"originalResponseStatus\":0,\"path\":\"/scanIssue/cmdInjectionIssue.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"cmd=test\",\"responseid\":0,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183:80/scanIssue/cmdInjectionIssue.do\",\"userid\":0}");

			//xst
			//requestJsonStr.add("{\"aftertest\":0,\"available\":true,\"createtime\":1608368178227,\"hash\":\"aaa526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=AA57F070445578\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scanIssue/xst/trace.do\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Type\\\":\\\"application/x-www-form-urlencoded\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"POST\",\"path\":\"/scanIssue/xst/trace.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"\",\"status\":\"Scheduled\",\"url\":\"http://10.0.1.183/scanIssue/xst/trace.do?url=http://10.0.1.183/scanIssue/xst/trace.do\",\"userid\":0}");

			//xxe
			requestJsonStr.add("{\"aftertest\":0,\"available\":true,\"createtime\":1608368178227,\"hash\":\"xxx526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=AA57F070445578\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scanIssue/json/xxe.do\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\",\\\"Content-Type\\\":\\\"application/json;charset=UTF-8\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"POST\",\"path\":\"/scanIssue/json/xxe.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"{\\\"xml\\\":\\\"%3C%3Fxml+version%3D%5C%221.0%5C%22+encoding%3D%5C%22UTF-8%5C%22%3F%3E%3Cxxe%3E%3Cname%3Eabc%3C%2Fname%3E%3Ccontent%3E%3Cid%3E1%3C%2Fid%3E%3Ctext%3Eabc%3C%2Ftext%3E%3C%2Fcontent%3E%3C%2Fxxe%3E\\\",\\\"chief\\\":{\\\"num\\\":\\\"no.1\\\",\\\"tech\\\":{\\\"name\\\":\\\"zs\\\",\\\"xml\\\":\\\"%3C%3Fxml+version%3D%5C%221.0%5C%22+encoding%3D%5C%22UTF-8%5C%22%3F%3E%3Cxxe%3E%3Cname%3Eabc%3C%2Fname%3E%3Ccontent%3E%3Cid%3E1%3C%2Fid%3E%3Ctext%3Eabc%3C%2Ftext%3E%3C%2Fcontent%3E%3C%2Fxxe%3E\\\"}}}\",\"status\":\"Scheduled\",\"url\":\"http://10.0.1.183scanIssue/json/xxe.do\",\"userid\":0}");
			requestJsonStr.add("{\"aftertest\":0,\"available\":true,\"createtime\":1608368178227,\"hash\":\"sss526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=AA57F070445578\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scanIssue/stream/xxe.do\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\",\\\"Content-Type\\\":\\\"text/plain;charset=UTF-8\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"POST\",\"path\":\"/scanIssue/stream/xxe.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;xxe&gt;&lt;name&gt;abc&lt;/name&gt;&lt;content&gt;&lt;id&gt;1&lt;/id&gt;&lt;text&gt;abc&lt;/text&gt;&lt;/content&gt;&lt;/xxe&gt;\",\"status\":\"Scheduled\",\"url\":\"http://10.0.1.183/scanIssue/stream/xxe.do\",\"userid\":0}");
			requestJsonStr.add("{\"aftertest\":0,\"available\":true,\"createtime\":1608368178227,\"hash\":\"sss526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=AA57F070445578\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scanIssue/query/xxe.do\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"GET\",\"path\":\"/scanIssue/query/xxe.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"status\":\"Scheduled\",\"url\":\"http://10.0.1.183/scanIssue/query/xxe.do\",\"query\":\"xml=%3C%3Fxml%20version%3D%221.0%22%20encoding%3D%22UTF-8%22%3F%3E%3Cxxe%3E%3Cname%3Eabc%3C%2Fname%3E%3Ccontent%3E%3Cid%3E1%3C%2Fid%3E%3Ctext%3Eabc%3C%2Ftext%3E%3C%2Fcontent%3E%3C%2Fxxe%3E\",\"userid\":0}");
			requestJsonStr.add("{\"aftertest\":0,\"available\":true,\"createtime\":1608368178227,\"hash\":\"fff526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=AA57F070445578\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scanIssue/form/xxe.do\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\",\\\"Content-Type\\\":\\\"application/x-www-form-urlencoded; charset=UTF-8\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"POST\",\"path\":\"/scanIssue/form/xxe.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"xml=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E++++%3Cxxe%3E%3Cname%3Eabc%3C%2Fname%3E%3Ccontent%3E%3Cid%3E1%3C%2Fid%3E%3Ctext%3Eabc%3C%2Ftext%3E%3C%2Fcontent%3E%3C%2Fxxe%3E\",\"status\":\"Scheduled\",\"url\":\"10.0.1.183/scanIssue/form/xxe.do\",\"userid\":0}");

			//unvalidated redirect
//			String unvalidDirect = "{\"aftertest\":0,\"available\":true,\"createtime\":1608368188227,\"hash\":\"uuu526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://127.0.0.1\\\",\\\"Cookie\\\":\\\"JSESSIONID=UnvalidDirect\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://localhost/scanIssue/direct/redirect.do\\\",\\\"Host\\\":\\\"localhost\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\"}\",\"host\":\"localhost\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"GET\",\"path\":\"/scanIssue/direct/redirect.do\",\"pluginid\":21300,\"port\":80,\"protocol\":\"http\",\"query\":\"url=/scanIssue/redirect.do\",\"status\":\"Scheduled\",\"url\":\"http://localhost/scanIssue/direct/redirect.do\",\"userid\":0}";
//			String unvalidHttp = "{\"aftertest\":0,\"available\":true,\"createtime\":1608368188227,\"hash\":\"uuu526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://127.0.0.1\\\",\\\"Cookie\\\":\\\"JSESSIONID=UnvalidHttp\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://localhost/scanIssue/http/redirect.do\\\",\\\"Host\\\":\\\"localhost\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\"}\",\"host\":\"localhost\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"GET\",\"path\":\"/scanIssue/http/redirect.do\",\"pluginid\":21300,\"port\":80,\"protocol\":\"http\",\"query\":\"url=http://localhost/scanIssue/redirect.do\",\"status\":\"Scheduled\",\"url\":\"http://localhost/scanIssue/http/redirect.do\",\"userid\":0}";
//			String unvalidPrefixSolidus = "{\"aftertest\":0,\"available\":true,\"createtime\":1608368188227,\"hash\":\"uuu526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://localhost\\\",\\\"Cookie\\\":\\\"JSESSIONID=UnvalidPrefixSolidus\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://localhost/scanIssue/regularPrefix_solidus/redirect.do\\\",\\\"Host\\\":\\\"localhost\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\"}\",\"host\":\"localhost\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"GET\",\"path\":\"/scanIssue/regularPrefix_solidus/redirect.do\",\"pluginid\":21300,\"port\":80,\"protocol\":\"http\",\"query\":\"url=http://localhost/scanIssue/redirect.do\",\"status\":\"Scheduled\",\"url\":\"http://localhost/scanIssue/regularPrefix_solidus/redirect.do\",\"userid\":0}";
//			String unvalidPostfixTopDomain= "{\"aftertest\":0,\"available\":true,\"createtime\":1608368188227,\"hash\":\"uuu526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://spring.demo.com\\\",\\\"Cookie\\\":\\\"JSESSIONID=UnvalidPostfixTopDomain\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://spring.demo.com/scanIssue/regularPostfix_top_domain/redirect.do\\\",\\\"Host\\\":\\\"spring.demo.com\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\"}\",\"host\":\"spring.demo.com\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"GET\",\"path\":\"/scanIssue/regularPostfix_top_domain/redirect.do\",\"pluginid\":21300,\"port\":80,\"protocol\":\"http\",\"query\":\"url=http://spring.demo.com\",\"status\":\"Scheduled\",\"url\":\"http://spring.demo.com/scanIssue/regularPostfix_top_domain/redirect.do\",\"userid\":0}";
//			String unvalidPostfixDomain= "{\"aftertest\":0,\"available\":true,\"createtime\":1608368188227,\"hash\":\"uuu526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://spring.demo.com\\\",\\\"Cookie\\\":\\\"JSESSIONID=UnvalidPostfixDomain\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://spring.demo.com/scanIssue/regularPostfix_domain/redirect.do\\\",\\\"Host\\\":\\\"spring.demo.com\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\"}\",\"host\":\"spring.demo.com\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"GET\",\"path\":\"/scanIssue/regularPostfix_domain/redirect.do\",\"pluginid\":21300,\"port\":80,\"protocol\":\"http\",\"query\":\"url=http://spring.demo.com\",\"status\":\"Scheduled\",\"url\":\"http://spring.demo.com/scanIssue/regularPostfix_domain/redirect.do\",\"userid\":0}";
//			String unvalidEscape= "{\"aftertest\":0,\"available\":true,\"createtime\":1608368188227,\"hash\":\"uuu526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"http://spring.demo.com\\\",\\\"Cookie\\\":\\\"JSESSIONID=UnvalidEscape\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://spring.demo.com/scanIssue/escape_special_character/redirect.do\\\",\\\"Host\\\":\\\"spring.demo.com\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\"}\",\"host\":\"spring.demo.com\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"GET\",\"path\":\"/scanIssue/escape_special_character/redirect.do\",\"pluginid\":21300,\"port\":80,\"protocol\":\"http\",\"query\":\"url=/scanIssue/redirect.do\",\"status\":\"Scheduled\",\"url\":\"http://spring.demo.com/scanIssue/escape_special_character/redirect.do\",\"userid\":0}";
//			requestJsonStr.add(unvalidDirect);requestJsonStr.add(unvalidHttp);
//			requestJsonStr.add(unvalidPrefixSolidus);requestJsonStr.add(unvalidPostfixTopDomain);
//			requestJsonStr.add(unvalidPostfixDomain);requestJsonStr.add(unvalidEscape);
//			//webdav
//			String webdav= "{\"aftertest\":0,\"available\":true,\"createtime\":1608368198227,\"hash\":\"vvv526b07fa99dac2c9202efe9379cf42f\",\"headers\":\"{\\\"Origin\\\":\\\"www.webdavserver.com\\\",\\\"Cookie\\\":\\\"userId=User9ec38b6;JSESSIONID=webdav\\\",\\\"Accept\\\":\\\"*/*\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://www.webdavserver.com/User9ec38b6\\\",\\\"Host\\\":\\\"www.webdavserver.com\\\",\\\"Accept-Language\\\":\\\"h-CN,zh;q=0.9\\\"}\",\"host\":\"www.webdavserver.com\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"method\":\"GET\",\"pluginid\":21400,\"port\":80,\"protocol\":\"http\",\"query\":\"url=/scanIssue/webdav.do\",\"status\":\"Scheduled\",\"url\":\"http://www.webdavserver.com/User9ec38b6/Products/\",\"userid\":0}";
//			requestJsonStr.add(webdav);

			String pathTaraversal = "{\"aftertest\":0,\"available\":true,\"caseId\":\"ef22f00d19fd4df0bd659ffc5eb87c5a\",\"contrastOriginalRequest\":false,\"createtime\":1614842213015,\"hash\":\"b63126f1fad8ddd1e16db9aeaf72bcfc2b2f3c8882ede8bd16de37b4e43ddb75\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=558599F26151BF8067903D7AE0BA0323\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scan/pathtraversal/pathTraversalIssue.jsp\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"23\\\",\\\"Content-Type\\\":\\\"application/x-www-form-urlencoded\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"lastupdate\":0,\"method\":\"POST\",\"originalResponseStatus\":0,\"path\":\"/scanIssue/pathTraversal.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"path=/etc/11111\",\"responseid\":0,\"sentAsync\":false,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183:80/scanIssue/pathTraversal.do\",\"userid\":0,\"webdavMethod\":false}";
			String pathTaraversalQueryString = "{\"aftertest\":0,\"available\":true,\"caseId\":\"13b9aed0e6fe487fbe854459b9d6d08a\",\"contrastOriginalRequest\":false,\"createtime\":1614841803676,\"hash\":\"f7b9c6296f2fe2c8f64a7bdaca8174ea131e7fcc0d609094708dfbb5b9d7091f\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=558599F26151BF8067903D7AE0BA0323\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scan/pathtraversal/pathTraversalIssue.jsp\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"23\\\",\\\"Content-Type\\\":\\\"application/json\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"lastupdate\":0,\"method\":\"GET\",\"originalResponseStatus\":0,\"path\":\"/scanIssue/pathTraversalGet.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"query\":\"path=/etc/33333\",\"responseid\":0,\"sentAsync\":false,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183:80/scanIssue/pathTraversalGet.do\",\"userid\":0,\"webdavMethod\":false}";
			String pathTaraversalJsonString = "{\"aftertest\":0,\"available\":true,\"caseId\":\"d239cd9c541c41b3bd0ebc9c1b86d4b2\",\"contrastOriginalRequest\":false,\"createtime\":1614841138124,\"hash\":\"c6ee35204db1a167e774ff74a62d72fa4e281a40f4208fa1be91d13a0c5e6f97\",\"headers\":\"{\\\"Origin\\\":\\\"http://10.0.1.183\\\",\\\"Cookie\\\":\\\"JSESSIONID=558599F26151BF8067903D7AE0BA0323\\\",\\\"Accept\\\":\\\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\\\",\\\"Upgrade-Insecure-Requests\\\":\\\"1\\\",\\\"User-Agent\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0\\\",\\\"Connection\\\":\\\"keep-alive\\\",\\\"Referer\\\":\\\"http://10.0.1.183/scan/pathtraversal/pathTraversalIssue.jsp\\\",\\\"Host\\\":\\\"10.0.1.183\\\",\\\"Accept-Language\\\":\\\"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\\\",\\\"Content-Length\\\":\\\"23\\\",\\\"Content-Type\\\":\\\"application/json\\\"}\",\"host\":\"10.0.1.183\",\"httpversion\":\"HTTP/1.1\",\"id\":0,\"lastupdate\":0,\"method\":\"POST\",\"originalResponseStatus\":0,\"path\":\"/scanIssue/jsonPathTraversal.do\",\"pluginid\":0,\"port\":80,\"protocol\":\"http\",\"requestbody\":\"{\\\"path\\\":\\\"/etc/2222\\\"}\",\"responseid\":0,\"sentAsync\":false,\"status\":\"Scheduled\",\"testtype\":\"Scan\",\"url\":\"http://10.0.1.183:80/scanIssue/jsonPathTraversal.do\",\"userid\":0,\"webdavMethod\":false}";
			requestJsonStr.add(pathTaraversal);
			requestJsonStr.add(pathTaraversalQueryString);
			requestJsonStr.add(pathTaraversalJsonString);

			for (String jsonStr : requestJsonStr) {
				// 创建消息
				TextMessage textMessage = session.createTextMessage(jsonStr);
				// 发送消息
				producer.send(textMessage);
			}
			connection.close();
			PrintWriter out = response.getWriter();
			out.println("Message sent to queue successfully");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/receiveTest.do")
	public void receiveTest(HttpServletRequest request, HttpServletResponse response) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(bugQueueName);
			MessageConsumer consumer = session.createConsumer(destination);
			PrintWriter out = response.getWriter();
			while (true) {
				TextMessage message = (TextMessage) consumer.receive(500);// 阻塞接受,还可以指定等待时间或者不阻塞接受,没有就跳过
				if (message == null) {
					break;
				}
				out.println("接收响应结果:" + message.getText());
			}
			session.close();
			connection.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试allowed-methods漏洞
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/allowedMethods.do")
	public void allowedMethods(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    PrintWriter out = response.getWriter();
        out.println("AllowedMethods test, Http Method: \"" + request.getMethod() + "\" Was Allowed!");
        out.close();
	}
	
	/**
	 * 测试responseHeaders漏洞
	 * @param request
	 * @param response
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	@RequestMapping(value = "/responseHeaders.do")
	public void responseHeadersTest(HttpServletRequest request, HttpServletResponse response,
									@RequestParam String key, @RequestParam String value) throws IOException{
		if (StringUtils.isNotBlank(key)) {
			response.setHeader(key, value);
		}
		PrintWriter out = response.getWriter();
        out.println("ResponseHeaders test, the specified response header is : " + key + ":" +value);
        out.close();
	}

	/**
	 * 测试backup-directories漏洞
	 * @param request
	 * @param response
	 * @param directory 目录
	 * @param fileName 文件名
	 * @throws IOException 
	 */
	@RequestMapping(value = "/backupDirectories.do")
	public void backupDirectoriesTest(HttpServletRequest request, HttpServletResponse response,
									  @RequestParam String directory, @RequestParam String fileName) throws IOException {
//	    response.setContentType("text/html; charset=UTF-8");
		// 从请求中获取待下载的文件名
	    directory = directory.endsWith(File.separator) ? directory : directory + File.separator;
		File file = new File(directory + fileName);
		// windows系统文件夹名和文件名不区分大小写，可能存在漏洞多报
		if (file.exists()) {
			try(InputStream bis = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());) {
				String filename = URLEncoder.encode(fileName, "UTF-8");
				response.addHeader("Content-Disposition", "attachment;filename=" + filename);
				response.setContentType("application/octet-stream");
				int len = 0;
				// 读取文件内容
				while ((len = bis.read()) != -1) {
					// 把读取到文件内容写进response中；
					out.write(len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
//		    PrintWriter out = response.getWriter();
//	        out.println("backup-directories test, '" + directory + fileName + "' file exists!");
//	        out.close();
		} else {
		    throw new FileNotFoundException();
		}
	}

	/**
	 * ldap-injection 漏洞检测
	 * @param request
	 * @param response
	 * @throws NamingException
	 * @throws IOException
	 */
	@RequestMapping("/testLdapInject.do")
	public void testLdapInjection(HttpServletRequest request, HttpServletResponse response) throws NamingException, IOException {
	    String uid = request.getParameter("uid");
	    if (StringUtils.isBlank(uid)) {
	        uid = "*";
	    }
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://10.0.1.117:389");
		env.put(Context.SECURITY_PRINCIPAL, "cn=Manager,dc=seczone,dc=cn" );
		env.put(Context.SECURITY_CREDENTIALS, "Seczonels" );
		InitialDirContext idc = null;
		try {
			idc = new InitialDirContext(env);
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrPersonArray = { "uid"};
			constraints.setReturningAttributes(attrPersonArray);

			NamingEnumeration<SearchResult> results = idc.search("ou=User,dc=seczone,dc=cn", "(&(objectClass=*)(uid=" + uid + "))", constraints);
			while (results != null && results.hasMore()) {
				SearchResult sr = results.next();
				String dn = sr.getName();
				PrintWriter out = response.getWriter();
				out.println(dn);
			}
		} finally {
			if (idc != null) {
				idc.close();
			}
		}
	}
	
	/**
	 * session-fixation 漏洞检测，form
	 * @param request
	 * @param response
	 * @param cookie
	 * @throws IOException 
	 */
	@RequestMapping(value = "/sessionFixationForm.do", method = RequestMethod.POST)
	public void sessionFixationFormTest(HttpServletRequest request, HttpServletResponse response,
										@RequestParam String userName,
										@RequestParam String password,
										@RequestParam(required = false, value = "Set-Cookie") String cookie) throws IOException {
	    String reqHeadCk = request.getHeader("Set-Cookie");
		System.out.println("request header Set-Cookie:" + reqHeadCk);
		System.out.println("form Set-Cookie:" + cookie);

		cookie = StringUtils.isBlank(reqHeadCk) ? cookie : reqHeadCk;
		if (StringUtils.isBlank(cookie)) {
			response.addCookie(new Cookie("JSESSIONID","364f6BE921F3262E8563052cABD863D6"));
		    return;
		}

		String[] split = cookie.split("=");
		Cookie ck = new Cookie(split[0], split[1]);
		response.addCookie(ck);
		System.out.println("response Set-Cookie:" + cookie);

		PrintWriter writer = response.getWriter();
		writer.print("response Set-Cookie:" + cookie);
		writer.close();
	}

	/**
	 * session-fixation 漏洞检测，json
	 * @param request
	 * @param response
	 * @param map
	 * @throws IOException 
	 */
	@RequestMapping(value = "/sessionFixationJson.do", method = RequestMethod.POST)
	public void sessionFixationJsonTest(HttpServletRequest request, HttpServletResponse response,
										@RequestBody(required = false)Map<String, String> map) throws IOException {
	    String reqHeadCk = request.getHeader("Set-Cookie");
        System.out.println("request header Set-Cookie:" + reqHeadCk);
		String cookie = map.get("Set-Cookie");
		System.out.println("json Set-Cookie:" + cookie);

		cookie = StringUtils.isBlank(reqHeadCk) ? cookie : reqHeadCk;
		if (StringUtils.isBlank(cookie)) {
		    response.addCookie(new Cookie("JSESSIONID","364f6BE921F3262E8563052cABD863D6"));
            return;
        }

		String[] split = cookie.split("=");
        Cookie ck = new Cookie(split[0], split[1]);
		response.addCookie(ck);
		System.out.println("response Set-Cookie:" + cookie);

		PrintWriter writer = response.getWriter();
		writer.print("response Set-Cookie:" + cookie);
		writer.close();
	}

	@RequestMapping(value = "/rfiTest.do")
	public void rfiTest(HttpServletRequest request, HttpServletResponse response) {
	    response.setContentType("text/html; charset=UTF-8");
		String fileName = request.getParameter("fileName");
		String[] split = fileName.split(":");
		try (PrintWriter writer = response.getWriter();) {
			if (split[0].equalsIgnoreCase("http") || split[0].equalsIgnoreCase("https")) {
				// 从接口下载文件
				URL url = new URL(fileName);
				// 连接类的父类，抽象类
				URLConnection urlConnection = url.openConnection();
				// http的连接类
				HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
				// 设定请求的方法，默认是GET
				httpURLConnection.setRequestMethod(RequestMethod.GET.name());
				// 设置字符编码
				httpURLConnection.setRequestProperty("Charset", StandardCharsets.UTF_8.name());
				//状态码 200：成功连接
				int code = httpURLConnection.getResponseCode();
				if (code == 200) {
					try (InputStream inputStream = httpURLConnection.getInputStream();) {
						int len = 0;
						while ((len = inputStream.read()) != -1) {
							writer.write(len);
						}
					}
				} else {
					throw new RuntimeException("下载远程文件失败");
				}
			} else {
				// 从磁盘获取文件
				File file = new File(fileName);
				if (!file.isDirectory() && file.exists()) {
					try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
						String line = "";
						while (StringUtils.isNotBlank(line = reader.readLine())) {
							writer.println(line);
						}
					}
					return;
				} else {
				    throw new RuntimeException("下载远程文件失败");
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("下载远程文件失败", e);
		}
	}

	/**
	 * source-code-disclosure 漏洞检测
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/testSCD.do")
	public void testSCD(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = request.getParameter("fileName");
		String path = this.getClass().getClassLoader().getResource("/").getPath();
		File file = new File(path + fileName);
		try (InputStream bis = new BufferedInputStream(new FileInputStream(file));
		        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());) {
            
			String filename = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.setContentType("multipart/form-data");
			int len = 0;
			// 读取文件内容
			while ((len = bis.read()) != -1) {
				// 把读取到文件内容写进response中；
				out.write(len);
			}
		}
	}

}
