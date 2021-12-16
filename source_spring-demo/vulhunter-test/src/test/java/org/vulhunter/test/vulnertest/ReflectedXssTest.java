package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class ReflectedXssTest {
	
//	private static String domain = "http://localhost:8086/spring-demo";
	
	/**
	 * reflected-xss 
	 * engine report 34 xss
	 */
	@Test
	public void writeInternal() {
		String path = "/reflectedXss/writeInternal.do?username=" + "test-username";
		HttpClientUtil.get(path);
	}

	@Test
	public void jspWriterTest() {
		String path = "/reflectedXss/jspWriterTest.do?username=" + "test-username" + "&pwd="
				+ "test-pwd" + "&encodingString=" + "tag-fields";
		HttpClientUtil.get(path);
	}

	@Test
	public void servletOutputStreamTest() {
		String path = "/reflectedXss/servletOutputStreamTest.do?testString=" + "test-String";
		HttpClientUtil.get(path);
	}

	@Test
	public void printWriterTestFormat() {
		String path = "/reflectedXss/printWriterTest-format.do?testString=" + "test-fields";
		HttpClientUtil.get(path);
	}

	public void printWriterTestPrint() {
		String path = "/reflectedXss/printWriterTest-print.do?testString=" + "test-fields";
		HttpClientUtil.get(path);
	}

	@Test
	public void printWriterTestPrintf() {
		String path = "/reflectedXss/printWriterTest-printf.do?testString=" + "test-fields";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void printWriterTestPrintln() {
		String path = "/reflectedXss/printWriterTest-println.do?testString=" + "test-fields";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void printWriterTestWrite() {
		String path = "/reflectedXss/printWriterTest-write.do?testString=" + "test-fields";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void writeRaw() {
		String path = "/reflectedXss/writeRaw.do?testString=" + "test-fields";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void htmlResponseWriter() {
		String path = "/reflectedXss/HtmlResponseWriter.do?testString=" + "test-fields";
		HttpClientUtil.get(path);
	}

}
