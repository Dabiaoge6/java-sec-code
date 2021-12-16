package org.spring.demo.crawler.tool;

import org.apache.http.client.methods.HttpRequestBase;
import org.w3c.dom.Node;

import java.util.List;

public abstract class AbstractTestCaseRequest {

	private String fullURL;
	private String query;
	private String payload;
	private boolean passed;
	private List<Node> headers;
	private List<Node> cookies;
	private List<Node> getParams;
	private List<Node> formParams;

	public AbstractTestCaseRequest(String fullURL, List<Node> headers, List<Node> cookies,
                                   List<Node> getParams, List<Node> formParams, String payload) {
		this.fullURL = fullURL;
		this.headers = headers;
		this.cookies = cookies;
		this.getParams = getParams;
		this.formParams = formParams;
		this.payload = payload;
	}

	public HttpRequestBase buildRequest() {
		buildQueryString();
		HttpRequestBase request = createRequestInstance(fullURL + query);
		buildHeaders(request);
		buildCookies(request);
		buildBodyParameters(request);
		return request;
	}

	/**
	 * Method to create a POST, GET, DELETE, HEAD, OPTIONS, TRACE request
	 * object.
	 * 
	 * @return an instance of a subclass of HttpRequestBase
	 */
	abstract HttpRequestBase createRequestInstance(String URL);

	/**
	 * Defines how to construct URL query string.
	 */
	abstract void buildQueryString();

	/**
	 * Defines what headers will be send.
	 */
	abstract void buildHeaders(HttpRequestBase request);

	/**
	 * Defines what cookies will be send.
	 */
	abstract void buildCookies(HttpRequestBase request);

	/**
	 * Defines what parameter on the body will be send.
	 */
	abstract void buildBodyParameters(HttpRequestBase request);

	public String getFullURL() {
		return fullURL;
	}

	public void setFullURL(String fullURL) {
		this.fullURL = fullURL;
	}

	public List<Node> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Node> headers) {
		this.headers = headers;
	}

	public List<Node> getCookies() {
		return cookies;
	}

	public void setCookies(List<Node> cookies) {
		this.cookies = cookies;
	}

	public List<Node> getGetParams() {
		return getParams;
	}

	public void setGetParams(List<Node> getParams) {
		this.getParams = getParams;
	}

	public List<Node> getFormParams() {
		return formParams;
	}

	public void setFormParams(List<Node> formParams) {
		this.formParams = formParams;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	@Override
	public String toString() {
		return "AbstractTestCaseRequest [fullURL=" + fullURL + ", query=" + query +  ", payload="
				+ payload +  ", passed=" + passed + ", headers=" + headers + ", cookies=" + cookies
				+ ", getParams=" + getParams + ", formParams=" + formParams + "]";
	}
	
	
}
