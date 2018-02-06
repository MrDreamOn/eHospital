package com.hospital.register.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.hospital.register.vo.PublicNoConfigVo;

public class HttpRequest {
    private static Logger                             logger = LoggerFactory
        .getLogger(HttpRequest.class);
    private static PoolingHttpClientConnectionManager cm;

    private static HttpRequestRetryHandler            httpRequestRetryHandler;

    //    private static final Integer                      CONNECTION_TIMOUT = 15 * 1000;                      // 请求超时时间
    //    private static final Integer                      SO_TIMOUT         = 15 * 1000;                      // 设置等待数据超时时间

    static {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = wrapClient();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
            .<ConnectionSocketFactory> create().register("http", plainsf).register("https", sslsf)
            .build();
        cm = new PoolingHttpClientConnectionManager(registry);
        cm.setDefaultMaxPerRoute(100);
        cm.setMaxTotal(2000);
        //请求重试处理
        httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount,
                                        HttpContext context) {
                if (executionCount >= 5) {// 如果已经重试了5次，就放弃                    
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试                    
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常                    
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时                    
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达                    
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝                    
                    return false;
                }
                if (exception instanceof SSLException) {// ssl握手异常                    
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                org.apache.http.HttpRequest httpRequest = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(httpRequest instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
    }

    public static LayeredConnectionSocketFactory wrapClient() {
        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null).build();
        } catch (KeyManagementException e) {
            logger.error(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (KeyStoreException e) {
            logger.error(e.getMessage());
        }
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return sslsf;
    }

    //	public static SSLSocketFactory wrapClient(KeyStore keyStore){
    //		
    //		//SSLContexts 
    //	}
    /**
     * 微信OAuth认证接口
     * 
     * @param code
     * @return
     * @throws Exception
     */
    public static Map<String, String> sendToWechatOAuth(String code) {
        Map<String, String> map = new HashMap<String, String>();
        String s = "";
        StringBuffer sb = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        sb.append(PublicNoConfigVo.APPID);
        sb.append("&secret=");
        sb.append(PublicNoConfigVo.APPSECRET);
        sb.append("&code=");
        sb.append(code);
        sb.append("&grant_type=authorization_code");
        HttpGet post = new HttpGet(sb.toString());
        try {
            CloseableHttpResponse response = null;
            ;
            try {
                response = getHttpClient().execute(post);
                HttpEntity httpEntity = response.getEntity();
                try {
                    s = EntityUtils.toString(httpEntity);
                } finally {
                    if (httpEntity != null) {
                        httpEntity.getContent().close();
                    }
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            logger.info("获取OAuth返回结果：" + s);
            if (StringUtils.hasText(s)) {
                JSONObject json = new JSONObject(s);
                String openid = json.getString("openid");
                String token = json.getString("access_token");
                map.put("openid", openid);
                map.put("token", token);
                return map;
            } else {
                map.put("err", s);
                return map;
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            map.put("err", "err10002");
            return map;
        } catch (JSONException e) {
            logger.error(e.getMessage());
            map.put("err", "err20001");
            return map;
        } catch (IOException e) {
            logger.error(e.getMessage());
            map.put("err", "err10001");
            return map;
        } finally {
            post.releaseConnection();
        }

    }

    public static CloseableHttpClient getHttpClient() {

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(30000)
            .setConnectTimeout(45000).setSocketTimeout(45000).build();
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm)
            .setRetryHandler(httpRequestRetryHandler).setDefaultRequestConfig(requestConfig)
            .build();
        return httpclient;
    }

}