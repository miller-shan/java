package com.github.miller.shan.testng;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 15:57
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 参数化, 支持8中基本的数据类型.Parameters参数一般是写在TestNG的配置文件中的.
 **/
public class TestNGParameters {

    @Test(description = "通过配置文件testng.xml方式运行测试，读取配置文件数据,值按照顺序传递给方法的参数")
    @Parameters({"arg1", "arg2"})
    public void testAdd(Integer arg11, Integer arg22) {
        System.out.println("arg11:" + arg11 + ", arg22:" + arg22);
        Calculator calculator = new Calculator();
        int result = calculator.add(arg11, arg22);
        Assert.assertEquals(result, 3, "计算出错");
    }
}
