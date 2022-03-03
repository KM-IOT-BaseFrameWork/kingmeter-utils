package com.kingmeter.utils;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author: crazyandy
 */
public class CloseableHttpClientBeanUtil {


    private static CloseableHttpClientBeanUtil instance;
    @Getter
    @Setter
    private CloseableHttpClient closeableHttpClient;

    private CloseableHttpClientBeanUtil() {}

    public static CloseableHttpClientBeanUtil getInstance() {
        if (instance == null) {
            synchronized (FileUtils.class) {
                if (instance == null) {
                    instance = new CloseableHttpClientBeanUtil();
                    try{
                        instance.setCloseableHttpClient(getCloseableHttpClientBean());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }


    private static CloseableHttpClient getCloseableHttpClientBean() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        // implement X509TrustManager , avoid validation
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[]{trustManager}, null);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sc))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connManager.setDefaultConnectionConfig(ConnectionConfig.DEFAULT);
        HttpClientBuilder builder = HttpClients.custom().setConnectionManager(connManager);
        return builder.build();
    }

}
