
/* RestServiceImpl.class */

public class RestServiceImpl extends AbstractRestServiceImpl {


  private final Log log = LogFactory.getLog(getClass());


  public Map<String, Object> invokeGet(String api, Map<String, Object> param) throws EgovBizException {
    String queryString = ConvertUtil.convertMapToQueryString(param);
    String sRet = httpGet(api, queryString);
    if (sRet == null || sRet.length() <= 0) {
      this.log.error("sRet is NULL!!! ");
      throw new EgovBizException("HTTP 요청에 오류가 발생하였습니다." );
    } 
    this.log.debug(sRet);
    return makeOutput(sRet);
  }
  
  public boolean invokeGet(String api, ByteBuffer out) throws EgovBizException {
    try {
      URL url = new URL(api);
      URLConnection connection = url.openConnection();
      InputStream raw = connection.getInputStream();
      InputStream in = new BufferedInputStream(raw);
      byte[] chunk = new byte[1024];
      int bytesRead = 0;
      while ((bytesRead = in.read(chunk)) != -1)
        out.put(chunk, 0, bytesRead); 
      out.flip();
      in.close();
    } catch (IOException e) {
      this.log.error("IOException!! invokeGet Binary 오류가 발생하였습니다.");
      throw new EgovBizException("invokeGet Binary 오류가 발생 하였습니다." );
    } 
    return true;
  }



}












