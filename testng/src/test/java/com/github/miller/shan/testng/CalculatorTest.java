package com.github.miller.shan.testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 10:57
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 测试加减乘除
 **/
//@Listeners({MyTestNGTestListener.class, MyExtentReporterListener.class})
public class CalculatorTest {

    @Test(description = "测试加法")
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        Assert.assertEquals(result, 3, "计算出错");
    }

    @Test(description = "测试减法")
    public void testSubtract() {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(1, 2);
        Assert.assertEquals(result, -1, "计算出错");
    }

    @Test(description = "测试乘法")
    public void testMultiply() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(1, 2);
        Assert.assertEquals(result, 2, "计算出错");
    }

    @Test(description = "测试除法")
    public void testDivide() {
        Calculator calculator = new Calculator();
        int result = calculator.divide(4, 2);
        Assert.assertEquals(result, 2, "计算出错");
    }


}
