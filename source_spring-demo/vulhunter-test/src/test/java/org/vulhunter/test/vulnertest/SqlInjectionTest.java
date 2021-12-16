package org.vulhunter.test.vulnertest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class SqlInjectionTest {
	
	/*
	 * 设置参数
	 */
	private List setParam() {
		List paramList = new ArrayList();
		paramList.add(new BasicNameValuePair("username", "test"));
		paramList.add(new BasicNameValuePair("pwd", "test password"));
		return paramList;
	}

	/*
	 * sqlinjection
	 * engine report 25 sqlinjection and 5 csrf
	 */
	@Test
	public void statementAddBatch() {
		String path = "/sqlInjection/statementAddBatch.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecute() {
		String path = "/sqlInjection/statementExecute-sql.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecuteInt() {
		String path = "/sqlInjection/statementExecute-sql-int.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecuteIntArr() {
		String path = "/sqlInjection/statementExecute-sql-intArr.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecuteStringArr() {
		String path = "/sqlInjection/statementExecute-sql-StringArr.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecuteQuery() {
		String path = "/sqlInjection/statementExecuteQuery.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecuteUpdate() {
		String path = "/sqlInjection/statementExecuteUpdate-sql.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecuteUpdateInt() {
		String path = "/sqlInjection/statementExecuteUpdate-sql-int.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecuteUpdateIntArr() {
		String path = "/sqlInjection/statementExecuteUpdate-sql-intArr.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void statementExecuteUpdateStringArr() {
		String path = "/sqlInjection/statementExecuteUpdate-sql-StringArr.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareCall() {
		String path = "/sqlInjection/connectionPrepareCall.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareCallInt2() {
		String path = "/sqlInjection/connectionPrepareCall-int2.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareCallInt3() {
		String path = "/sqlInjection/connectionPrepareCall-int3.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareStatement() {
		String path = "/sqlInjection/connectionPrepareStatement.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareStatementInt() {
		String path = "/sqlInjection/connectionPrepareStatement-int.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareStatementIntArr() {
		String path = "/sqlInjection/connectionPrepareStatement-intArr.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareStatementInt2() {
		String path = "/sqlInjection/connectionPrepareStatement-int2.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareStatementInt3() {
		String path = "/sqlInjection/connectionPrepareStatement-int3.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void connectionPrepareStatementStringArr() {
		String path = "/sqlInjection/connectionPrepareStatement-StringArr.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void createNativeQuery() {
		String path = "/sqlInjection/createNativeQuery.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void createNativeQueryString() {
		String path = "/sqlInjection/createNativeQuery-String.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

	@Test
	public void createNativeQueryClass() {
		String path = "/sqlInjection/createNativeQuery-Class.do";
		List paramList = setParam();
		HttpClientUtil.postForm(path,paramList);
	}

}
