
/* AbstractRestServiceImpl.class */


public abstract class AbstractRestServiceImpl implements RestService {

   private final Log log = LogFactory.getLog(getClass());
  
   protected String httpGet(String url, String queryString) throws EgovBizException {
       this.log.debug("HTTP Req URL :" + url);
       this.log.debug("HTTP Req queryString :" + queryString);

       HttpGetUtil httpGetUtil = new HttpGetUtil(url + "?" + queryString);
       httpGetUtil.addHeader("Cache-Control", "no-store");
       httpGetUtil.addHeader("Pragma", "no-cache");
       httpGetUtil.addHeader("Expires", "0");

       int status = httpGetUtil.Request();
       this.log.debug("HTTP status :" + status);

       return httpGetUtil.getBody();
    }


}







