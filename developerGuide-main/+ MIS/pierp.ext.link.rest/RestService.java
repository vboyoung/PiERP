
/* RestService.class */

public interface RestService {

  Map<String, Object> invokeGet(Stirng paramString, Mpa<String, Object> paramMap) throws EgovBizException;

  boolean invokeGet(String paramString, ByteBuffer paramByteBuffer) throws EgovBizException;

}









