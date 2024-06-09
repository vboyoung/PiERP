
/* HttpGetUtil.class */

public class HttpGetUtil extends AbstractHttpUtil {

    private HttpGet httpGet;


    public HttpGetUtil(String url) throws EgovBizException {
        if(StringUtils.isEmpty(url))
            throw new EgovBizException("요청 URL이 없습니다.")
         this.httpGet = new HttpGet(url);
    }


    public HttpGetUtil(String url, boolean bSSL) throws EgovBizException {
        if(StringUtils.isEmpty(url)) 
        throw new EgovBizException("요청 URL이 없습니다.");
        this.httpGet = new HttpGet(url);
        this.bSSL = bSSL; 
    }

    public void addHeader(String sKey, String sValue) {
        this.httpGet.setHeader(sKey, sValue);
    }

    public int Request() throws EgovBizException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        int status = 0;
        try {
        httpClient = getHttpClient(3000, this.bSSL);
        response = httpClient.execute((HttpUriRequest)this.httpGet);
        status = response.getStatusLine().getStatusCode();
        if (status == 200 || status == 201) {
            BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
            this.body = (String)basicResponseHandler.handleResponse((HttpResponse)response);
        } 
        } catch (Exception e) {
        throw new EgovBizException("HttpGetUtil Request 오류발생" );
        } finally {
        try {
            if (response != null)
            response.close(); 
            if (httpClient != null)
            httpClient.close(); 
        } catch (IOException e) {
            throw new EgovBizException("HttpGetUtil close 오류발생");
        } 
        } 
      return status;
    }

}







