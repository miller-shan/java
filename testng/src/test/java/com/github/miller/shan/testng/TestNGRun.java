package com.github.miller.shan.testng;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-30 17:53
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 使用代码方式运行TestNG
 **/
public class TestNGRun {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setOutputDirectory("testng/reports/test-output"); // 设置testNG报告输出目录为自定义目录

        testNG.setTestClasses(new Class[]{CalculatorTest.class}); // 指定我们想要执行哪些测试类
        TestListenerAdapter listenerAdapter = new TestListenerAdapter(); // TestListenerAdapter类实现了ITestListener接口
        testNG.addListener(listenerAdapter); // 添加TestNG提供的监听器
        testNG.addListener(new MyTestNGTestListener()); // 添加自定义监听器
        testNG.addListener(new MyExtentReporterListener());
        testNG.run();
        System.out.println("PASSED:" + listenerAdapter.getPassedTests().size());

    }
}
