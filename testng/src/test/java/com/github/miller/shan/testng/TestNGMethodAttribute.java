package com.github.miller.shan.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 15:49
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 测试方法的属性。建议少用吧，为了兼容其他测试框架，比如Junit
 **/
public class TestNGMethodAttribute {

    @Test(description = "是否执行此方法")
    public void testAdd() {
        System.out.println("testAdd invoked!");
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        Assert.assertEquals(result, 3, "计算出错");
    }

    /**
     * 如何此方法依赖的方法执行失败了，那么这个方法会被忽略，不执行
     */
    @Test(description = "方法的依赖", dependsOnMethods = {"testAdd"})
    public void testSubtract() {
        System.out.println("testSubtract invoked!");
        Calculator calculator = new Calculator();
        int result = calculator.subtract(1, 2);
        Assert.assertEquals(result, -1, "计算出错");
    }

    @Test(description = "运行多次测试方法", invocationCount = 10)
    public void testMultiply() {
        System.out.print("testMultiply invoked!");
        Calculator calculator = new Calculator();
        int result = calculator.multiply(1, 2);
        Assert.assertEquals(result, 2, "计算出错");
    }

    @Test(description = "指定测试运行最大时间", timeOut = 2000L)
    public void testDivide() {
        System.out.print("testDivide invoked!");
        Calculator calculator = new Calculator();
        int result = calculator.divide(4, 2);
        Assert.assertEquals(result, 2, "计算出错");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
