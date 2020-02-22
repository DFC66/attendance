package com.dfc.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * https工具类
 *
 * @author remainsu
 * 2019-05-05
 */
public class HttpsUtil {

    /**
     * post方式访问
     * @param url 路径
     * @param map 参数
     * @return
     */
    public static String httpsPost(String url, Map<String, String> map) {

        String charset = "UTF-8";
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;

        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * get方式访问（如果有参数直接 ?xx&yy&zz 的方式即可）
     * @param url
     * @return
     */
    public static String httpsGet(String url) {

        String charset = "UTF-8";
        HttpClient httpClient = null;
        HttpGet httpGet= null;
        String result = null;

        try {
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);

            HttpResponse response = httpClient.execute(httpGet);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("api_key","CX2aigbTpCnnDqf0F2O-0_78hfVIvlxA");
        map.put("api_secret","NodN9bNE113YiOweX6hnOgtjLxHY_uJ3");
        map.put("outer_id","666666");
        String response = HttpsUtil.httpsPost("https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail", map);
        System.out.println(response);
    }


}

class SSLClient extends DefaultHttpClient {
    //用于进行Https请求的HttpClient
    public SSLClient() throws Exception {
        super();
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException { }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException { }

            @Override
            public X509Certificate[] getAcceptedIssuers() {return null; }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));
    }
}
