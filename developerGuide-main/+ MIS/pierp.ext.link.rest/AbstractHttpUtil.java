
/* AbstractHttpUtil.class */



public abstract class AbstractHttpUtil {

    protected static final int timeout = 3000;
    protected boolean bSSL = false;
    protected String body;

    public String getBody() {
        return this.body;
    }

    public CloseableHttpClient getHttpClient(int timeout, boolean bSSL) throws EgovBizException { 
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).setConnectTimeout(timeout).build();
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(requestConfig);
    

        if (bSSL)
            try {
                SSLContexts.custom().useProtocol("SSL").loadTrustMaterial(null, new TrustStrategy() {
              public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
                return true;
              }
            }).build();
            httpClientBuilder.setSSLHostnameVerifier((HostnameVerifier)new NoopHostnameVerifier()).setSSLContext(sslcontext);
            }catch (KeyManagementException|java.security.NoSuchAlgorithmException|java.security.KeyStoreException e) {
                throw new EgovBizException("getHttpClient :" + e.getMessage());
             }
        return httpClientBuilder.build();  
    }

    public abstract void addHeader(String paramString1, String paramString2);
    
    public abstract int Request() throws EgovBizException;

}








