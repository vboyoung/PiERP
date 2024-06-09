package kier.portal.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.common.util.StringUtils;

/**
 *
 * @Class HttpPostUtil
 * @Description
 *
 * @author 정성희
 * @since 2020. 8. 6.
 * @version
 *
 */
public class HttpPostUtil extends AbstractHttpUtil{

	private HttpPost httpPost;
	private List<NameValuePair> postParams = new ArrayList<NameValuePair>();


	//생성자
	public HttpPostUtil(String url) throws EgovBizException{
		if(StringUtils.isEmpty(url)) {
			throw new EgovBizException("요청 URL이 없습니다.");
		}
		httpPost = new HttpPost(url);
	}


	//헤더 세팅 (ex : "Accept", "application/json" )
	@Override
	public void addHeader(String sKey, String sValue) {
		httpPost.setHeader(sKey, sValue);
	}

	//파라미터 세팅(POST만 가능)
	public void addParam(String sName, String sValue) {
		postParams.add(new BasicNameValuePair(sName, sValue));
	}

	@Override
	public int Request() throws EgovBizException  {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		int status = 0;
		try {
			//Post 방식은 Request body message에 전송
			HttpEntity postEntity = new UrlEncodedFormEntity(postParams, "UTF-8");
		    httpPost.setEntity(postEntity);

		    /*
		     * POST 는
		     * httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		     * 가 들어가 있어야 함.
		     *  jUnit (kier.portal.util.HttpPostTest) 호출 방법 참고!
		     */

			//httpClient = HttpClientBuilder.create().build();
		    httpClient = getHttpClient(timeout);

			response = httpClient.execute(httpPost);
			status = response.getStatusLine().getStatusCode();

			if(status == 200 || status == 201) {
				ResponseHandler<String> handler = new BasicResponseHandler();
		        body = handler.handleResponse(response);
			}

		}catch(Exception e) {
			throw new EgovBizException("HttpPostUtil Request 오류발생 :" + e.getMessage());
		}finally {
			try {
				if(response != null) response.close();
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				throw new EgovBizException("HttpPostUtil close 오류발생");
			}
		}
		return status;
	}

}

