package org.spring.demo.crawler.helper;

import org.spring.demo.crawler.tool.AbstractTestCaseRequest;
import org.spring.demo.crawler.tool.ServletTestCaseRequest;
import org.spring.demo.crawler.tool.XMLCrawler;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static String domain = "";

    public static List<AbstractTestCaseRequest> parseHttpFile(InputStream http, String domain) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        InputSource is = new InputSource(http);
        Document doc = docBuilder.parse(is);
        Node root = doc.getDocumentElement();

        List<AbstractTestCaseRequest> requests = new ArrayList<AbstractTestCaseRequest>();
        List<Node> tests = XMLCrawler.getNamedChildren("vulhunterTest", root);
        for (Node test : tests) {
            AbstractTestCaseRequest request = parseHttpTest(test, domain);
            requests.add(request);
        }
        return requests;
    }

    public static AbstractTestCaseRequest parseHttpTest(Node test, String domain) throws Exception {
        String URL = XMLCrawler.getAttributeValue("URL", test);
        String fullURL = domain + URL;
        String payload = "";

        List<Node> headers = XMLCrawler.getNamedChildren("header", test);
        List<Node> cookies = XMLCrawler.getNamedChildren("cookie", test);
        List<Node> getParams = XMLCrawler.getNamedChildren("getparam", test);
        List<Node> formParams = XMLCrawler.getNamedChildren("formparam", test);
        ServletTestCaseRequest svlTC = new ServletTestCaseRequest(fullURL, headers, cookies,
                getParams, formParams, payload);
        return svlTC;
    }
}
