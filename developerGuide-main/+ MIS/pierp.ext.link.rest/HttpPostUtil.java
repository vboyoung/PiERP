
/* HttpGetUtil.class */

public class HttpGetUtil extends AbstractHttpUtil {

     private HttpPost httpPost;
  
  private List<NameValuePair> postParams = new ArrayList<>();
  
  public HttpPostUtil(String url) throws EgovBizException {
    if (StringUtils.isEmpty(url))
      throw new EgovBizException("요청 URL이 없습니다."); 
    this.httpPost = new HttpPost(url);
  }
  
  public HttpPostUtil(String url, boolean bSSL) throws EgovBizException {
    if (StringUtils.isEmpty(url))
      throw new EgovBizException("요청 URL이 없습니다."); 
    this.httpPost = new HttpPost(url);
    this.bSSL = bSSL;
  }
  
  public void addHeader(String sKey, String sValue) {
    this.httpPost.setHeader(sKey, sValue);
  }
  
  public void addParam(String sName, String sValue) {
    this.postParams.add(new BasicNameValuePair(sName, sValue));
  }
  
  public int Request() throws EgovBizException {
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    int status = 0;
    try {
      UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(this.postParams, "UTF-8");
      this.httpPost.setEntity((HttpEntity)urlEncodedFormEntity);
      httpClient = getHttpClient(3000, this.bSSL);
      response = httpClient.execute((HttpUriRequest)this.httpPost);
      status = response.getStatusLine().getStatusCode();
      if (status == 200 || status == 201) {
        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
        this.body = (String)basicResponseHandler.handleResponse((HttpResponse)response);
      } 
    } catch (Exception e) {
      throw new EgovBizException("HttpPostUtil Request 오류발생 :" + e.getMessage());
    } finally {
      try {
        if (response != null)
          response.close(); 
        if (httpClient != null)
          httpClient.close(); 
      } catch (IOException e) {
        throw new EgovBizException("HttpPostUtil close 오류발생");
      } 
    } 
    return status;
  }

}








