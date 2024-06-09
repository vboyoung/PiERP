package kier.portal.common.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

public abstract class AbstractHttpUtil {

	protected static final int timeout = 1000*3;
	protected String body;

	public String getBody() {
		return body;
	}

	//Timeout 추가
	public CloseableHttpClient getHttpClient( int timeout ) {
		RequestConfig requestConfig = RequestConfig.custom()
										.setConnectionRequestTimeout(timeout)
										.setSocketTimeout(timeout)
										.setConnectTimeout(timeout)
										.build();

		HttpClientBuilder httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(requestConfig);
		return httpClientBuilder.build();
	}


	abstract public void addHeader(String sKey, String sValue);	//헤더세팅
	abstract public int Request() throws EgovBizException;		//HTTP 요청

}


