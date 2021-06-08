package com.github.miller.shan.testng;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-07-06 17:28
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 测试报告
 **/
@Listeners(value = {MyExtentReporterListener.class})
public class TestNGReport {
    @Test(description = "测试加法")
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        Assert.assertEquals(result, 3, "计算出错");
        Reporter.log("自定义报告测试加法", true);
    }

    @Test(description = "测试减法")
    public void testSubtract() {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(1, 2);
        Assert.assertEquals(result, -1, "计算出错");
        // false 则控制台不输出，直接输出到报告中
        Reporter.log("自定义报告测试减法", false);
    }
    @Test(description = "测试乘法")
    public void testMultiply() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(1, 2);
        Assert.assertEquals(result, 2, "计算出错");
    }


}
