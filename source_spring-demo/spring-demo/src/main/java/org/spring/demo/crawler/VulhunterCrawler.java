package org.spring.demo.crawler;

import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.spring.demo.crawler.helper.Utils;
import org.spring.demo.crawler.tool.AbstractTestCaseRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 爬虫发送请求
 * 1.创建vulhunter-crawler-http.xml
 * 2.准备爬虫
 * 2.1.生成CloseableHttpClient对象
 * 2.2.解析xml,将xml转换成request对象
 * 2.3.发送请求
 */
public class VulhunterCrawler {
    private static String domain =  "";

    protected void init() {
        try {
            String crawlerFile = System.getProperty("user.dir") + File.separator + "data" + File.separator
                    + "vulhunter-crawler-http.xml";
            crawl(new FileInputStream(crawlerFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void crawl(InputStream http) throws Exception {
        //生成CloseableHttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        long start = System.currentTimeMillis();

        //解析xml文件
        List<AbstractTestCaseRequest> requests = Utils.parseHttpFile(http,domain);
        for (AbstractTestCaseRequest request : requests) {
            try {
                //Thread.sleep(200);
                sendRequest(httpclient, request);
            } catch (Exception e) {
                System.err.println("\n  FAILED: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    protected ResponseInfo sendRequest(CloseableHttpClient httpclient, AbstractTestCaseRequest requestTC) {
        ResponseInfo responseInfo = new ResponseInfo();
        HttpRequestBase request = requestTC.buildRequest();
        responseInfo.setRequestBase(request);
        CloseableHttpResponse response = null;

        boolean isPost = request instanceof HttpPost;
        System.out.println((isPost ? "POST " : "GET ") + request.getURI());
        StopWatch watch = new StopWatch();

        watch.start();
        try {
            response = httpclient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        watch.stop();

        try {
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            responseInfo.setStatusCode(statusCode);
            double time = watch.getTime() / 1000;
            responseInfo.setTime(time);
            String outputString = "--> (" + String.valueOf(statusCode) + " : " + time + " sec) ";
            System.out.println(outputString);

            try {
                responseInfo.setResponseString(EntityUtils.toString(entity));
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return responseInfo;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("---开始处理---");
        for (String arg : args) {
            System.out.println("-- 参数 --：" + arg);
            domain = arg;
        }
        VulhunterCrawler crawler = new VulhunterCrawler();
        crawler.init();
    }
}

class ResponseInfo {
    private String responseString;
    private double time;
    private int statusCode;
    private HttpRequestBase requestBase;

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpRequestBase getRequestBase() {
        return requestBase;
    }

    public void setRequestBase(HttpRequestBase requestBase) {
        this.requestBase = requestBase;
    }
}
