package com.github.miller.shan.common.http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.util.StringUtils;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

/**
 * @Project: common
 * @Author: Miller
 * @Time: 2020-5-20 14:42:00
 * @Email: miller.shan.dd@gmail.com;miller.shan@aliyun.com
 * @Description:
 **/
public class HttpUtils {

    /**
     * 发送Get请求
     *
     * @param url     请求路径
     * @param params  路径参数，因为有些接口对路径参数有顺序要求，所以需要使用LinkedHashMap
     * @param headers 请求头数据
     * @return 响应结果
     */
    public static String sendGetRequest(String url, LinkedHashMap<String, Object> params, Map<String, Object> headers)
            throws ConnectException, UnknownHostException {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("url is empty.");
        }
        if (null == headers) {
            headers = new HashMap<>(16);
        }
        if (null == params) {
            params = new LinkedHashMap<>();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>=================GET请求日志");
        Response response = given().
                headers(headers).
                params(params).
                when().
                get(url).
                then().
                log().all().
                extract().response();
        String result = response.asString();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<=================GET响应日志");
        response.then().log().all();
        return result;
    }

    /**
     * 发送POST请求
     *
     * @param url             请求路径
     * @param queryParamsMap  路径参数，因为有些接口对路径参数有顺序要求，所以需要使用LinkedHashMap
     * @param headers         请求头数据
     * @param formDataMapBody form-data格式请求体内容
     * @param rawBody         raw格式请求体内容
     * @return 响应结果
     */
    public static String sendPostRequest(String url, LinkedHashMap<String, Object> queryParamsMap,
                                         Map<String, String> headers, Map<String, Object> formDataMapBody, Object rawBody)
            throws ConnectException, UnknownHostException {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("url is empty.");
        }
        if (null == headers) {
            headers = new HashMap<>(16);
        }
        if (null == queryParamsMap) {
            queryParamsMap = new LinkedHashMap<>(16);
        }
        if (null == formDataMapBody) {
            formDataMapBody = new HashMap<>(16);
        }
        if (null == rawBody) {
            rawBody = new Object();
        }
        // 配置请求
        RequestSpecification request = RestAssured.given();
//        request.config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")));
//        request.config(RestAssured.config().decoderConfig(decoderConfig().defaultContentCharset("UTF-8")));

        request.headers(headers);
        String contentType = String.valueOf(headers.get("Content-Type"));
        if (contentType.startsWith("application/json")) {
            request.queryParams(queryParamsMap);
            request.body(rawBody);
        } else if (contentType.startsWith("multipart/form-data")) {
//            RestAssured.config = RestAssuredConfig.config()
//                    .httpClient(HttpClientConfig.httpClientConfig()
//                            .httpMultipartMode(HttpMultipartMode.BROWSER_COMPATIBLE));
//            request.config(RestAssured.config().encoderConfig(encoderConfig().
//                    encodeContentTypeAs("multipart/form-data", ContentType.TEXT)));
            // 不支持设置编码，中文乱码
            request.config(RestAssured.config().encoderConfig(encoderConfig().
                    defaultCharsetForContentType("UTF-8", "multipart/form-data")));

            request.queryParams(queryParamsMap);
            for (String key : formDataMapBody.keySet()) {
                Object value = formDataMapBody.get(key);
                request.multiPart(key, value);
            }
        } else if (contentType.startsWith("application/x-www-form-urlencoded")) {
            request.config(RestAssured.config().encoderConfig(encoderConfig().
                    defaultCharsetForContentType("UTF-8", ContentType.URLENC)));
            request.queryParams(queryParamsMap);
            request.formParams(formDataMapBody);
        } else {
            request.params(queryParamsMap);
            request.body(rawBody);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>=================POST请求日志");
        request.log().all();
        Response response = request.when().post(url).then().extract().response();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<=================POST响应日志");
        response.then().log().all();
        String result = response.asString();
        return result;
    }
}
