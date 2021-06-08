package com.github.miller.shan.testng;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 16:04
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 多线程执行
 **/
public class TestNGMultiThread {
    Calculator calculator = null;

    @Test(threadPoolSize = 100, invocationCount = 100, enabled = false)
    public void testAdd() throws InterruptedException {
//        System.out.print("Test method executing on thread with id: " + Thread.currentThread().getId());
        int result = 0;
        Thread.sleep(100);
        if (calculator == null) {
            calculator = new Calculator();
            result = calculator.add(1, 2);
        } else {
            result = 4;
        }
        Assert.assertEquals(result, 3, "计算出错");
    }
}
