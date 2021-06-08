package com.github.miller.shan.javase.thread;

import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-07-20 11:16
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: https://dayarch.top/p/java8-completablefuture-tutorial.html
 **/
public class CompletableFutureTest {

    @Test
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = new CompletableFuture();

        Object o = completableFuture.get();
        completableFuture.complete("Future's Result Here Manually");
        System.out.println(o);
    }
}
