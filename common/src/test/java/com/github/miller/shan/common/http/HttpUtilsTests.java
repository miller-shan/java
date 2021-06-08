package com.github.miller.shan.common.http;

import org.testng.annotations.Test;

import java.net.ConnectException;
import java.net.UnknownHostException;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-08-03 15:05
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description:
 **/
public class HttpUtilsTests {

    @Test
    public void testConnectException() {
        String response = null;
        try {
            response = HttpUtils.sendGetRequest("https://www.testcccc.com", null, null);
        } catch (ConnectException connectException) {
            System.out.println("connectException......");
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException......");
        }

        System.out.println(response);
    }
}
