package com.kingmeter.utils;


import com.alibaba.fastjson.JSON;
import com.kingmeter.common.KingMeterMarker;
import com.kingmeter.common.ResponseCode;
import com.kingmeter.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class KMHttpClient {

    @Value("${kingmeter.business.baseUrl:url}")
    private String baseUrl = "";

    @Value("${kingmeter.tokenKey:tokenKey}")
    private String tokenKey;

    @Value("${kingmeter.tokenValue:tokenValue}")
    private String tokenValue;


    @Value("${log.http:true}")
    private boolean log_http = true;


    private ResponseData getResponseData(CloseableHttpResponse execute) throws IOException {
        CloseableHttpResponse response = execute;
        ResponseData responseData = new ResponseData();
        HttpEntity entity = response.getEntity();

        int status = response.getStatusLine().getStatusCode();
        //200 201 and so on ,all value stand for success
        if (status < HttpStatus.SC_OK ||
                status >= HttpStatus.SC_MULTIPLE_CHOICES) {
            responseData.setCode(status);
            responseData.setMessage(response.toString());
            return responseData;
        }

        responseData.setCode(status);

        if (entity != null) {
            responseData = JSON.parseObject(
                    EntityUtils.toString(entity, "UTF-8"),
                    ResponseData.class);
        }

        EntityUtils.consume(entity);
        response.close();
        return responseData;
    }

    private ResponseData execute(HttpRequestBase base, String url, long deviceId) {
        CloseableHttpClient client = null;
        ResponseData result = null;
        try {
            if (StringUtil.isNotEmpty(tokenKey)) {
                base.setHeader(tokenKey, tokenValue);
            }
            client = CloseableHttpClientBeanUtil.getInstance().getCloseableHttpClient();
            result = getResponseData(client.execute(base));
            log_response(url, deviceId, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log_response(url, deviceId, e.getMessage());
            base.abort();
            base.releaseConnection();
        } finally {
            if (result != null) {
                if (result.getCode() != HttpStatus.SC_OK) {
                    base.abort();
                    base.releaseConnection();
                }
            }
        }

        return new ResponseData(ResponseCode.NOT_FOUND, "");
    }

    public ResponseData get(Map<String, Object> params, String baseUrl, String mappingUrl, long deviceId) {
        StringBuffer sb = new StringBuffer();
        if (params.size() > 0) {
            sb.append("?");
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        String url = baseUrl + mappingUrl + sb.toString();

        log_request(url, deviceId, "GET", sb.toString());

        HttpGet get = new HttpGet(url);
//        RequestConfig.Builder builder = RequestConfig.custom();
//        builder.setSocketTimeout(3000)//3.1设置客户端等待服务端返回数据的超时时间
//                .setConnectTimeout(1000)//3.2设置客户端发起TCP连接请求的超时时间
//                .setConnectionRequestTimeout(1000);//3.3设置客户端从连接池获取链接的超时时间
//        get.setConfig(builder.build());
        return execute(get, url, deviceId);
    }

    public ResponseData get(Map<String, Object> params, String mappingUrl, long deviceId) {
        return get(params, baseUrl, mappingUrl, deviceId);
    }

    public ResponseData post(String data, String baseUrl, String mappingUrl, long deviceId) {
        String url = baseUrl + mappingUrl;
        HttpPost post = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(data, ContentType.APPLICATION_JSON);
        stringEntity.setContentType("application/json;charset=utf-8");
        post.setEntity(stringEntity);

        log_request(url, deviceId, "POST", data);

        return execute(post, url, deviceId);
    }

    public ResponseData post(String data, String mappingUrl, long deviceId) {
        return post(data, baseUrl, mappingUrl, deviceId);
    }

    public ResponseData put(String data, String mappingUrl, long deviceId) {
        return put(data, baseUrl, mappingUrl, deviceId);
    }

    public ResponseData put(String data, String baseUrl, String mappingUrl, long deviceId) {
        String url = baseUrl + mappingUrl;
        HttpPut put = new HttpPut(url);
        StringEntity stringEntity = new StringEntity(data, ContentType.APPLICATION_JSON);
        stringEntity.setContentType("application/json;charset=utf-8");
        put.setEntity(stringEntity);

        log_request(url, deviceId, "PUT", data);

        return execute(put, url, deviceId);
    }


    public ResponseData delete(String mappingUrl, long deviceId) {
        return delete(baseUrl, mappingUrl, deviceId);
    }

    public ResponseData delete(String baseUrl, String mappingUrl, long deviceId) {
        String url = baseUrl + mappingUrl;
        HttpDelete delete = new HttpDelete(url);

        log_request(url, deviceId, "DELETE", "");

        return execute(delete, url, deviceId);
    }


    private void log_request(String url, long deviceId, String method, String data) {
        if (log_http) {
            log.info(new KingMeterMarker("HTTP,5001"),
                    "{}|{}|{}|{}", deviceId, url, method, data);
        }
    }


    private void log_response(String url, long deviceId, String responseData) {
        if (log_http) {
            log.info(new KingMeterMarker("HTTP,5002"),
                    "{}|{}|{}", deviceId, url, responseData);
        }
    }

//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        KMHttpClient client = new KMHttpClient();
//        for (int i = 0; i < 10; i++) {
//            client.get(map, "http://127.0.0.1:8080/demo", 1);
//            client.get(map, "http://127.0.0.1:8080/demo2", 1);
//        }
//    }

}

