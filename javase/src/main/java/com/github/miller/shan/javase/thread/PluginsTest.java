package com.github.miller.shan.javase.thread;

import com.github.miller.shan.common.http.HttpUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-07-21 17:27
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description:
 **/
public class PluginsTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        FutureTask futureTask1 = new FutureTask(new TestHttp());
        FutureTask futureTask2 = new FutureTask(new TestHttps());

        executorService.submit(futureTask1);
        executorService.submit(futureTask2);

        executorService.shutdown();
    }

    static class TestHttp implements Callable<String> {
        @Override
        public String call() throws Exception {
            while (true) {
                String response = HttpUtils.sendGetRequest("http://swac.5iss.cn:9180/GetUniqueID?order=[1,2,3]", null, null);
                LocalDateTime now = LocalDateTime.now();
                System.out.println(now + " http响应结果:" + response);
                Thread.sleep(100);
            }
        }
    }

    static class TestHttps implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                while (true) {
                    String response = HttpUtils.sendGetRequest("https://swac.5iss.cn:9181/GetUniqueID?order=[1,2,3]", null, null);
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(now + " https响应结果:" + response);
                    Thread.sleep(100);
                }
            }catch (java.net.ConnectException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

}

