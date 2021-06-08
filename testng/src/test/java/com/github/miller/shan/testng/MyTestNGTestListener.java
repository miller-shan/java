package com.github.miller.shan.testng;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-30 17:56
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: TestListenerAdapter类很有用，因为它可以让我们很方便地获取测试结果的程序清单。
 * 如何自定义监听器？ 一般是自己编写一个类继承自TestListenerAdapter，重要的是要记得调用被覆写的方法的 super() 版本。
 **/
public class MyTestNGTestListener extends TestListenerAdapter {
    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);

        Reporter.log(String.format("-------------Result: %s-------------", tr.getMethod().getMethodName()), true);
        System.out.println("onTestSuccess invoked...");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);

        Reporter.log(String.format("-------------Result: %s-------------", tr.getMethod().getMethodName()), true);
        System.out.println("onTestFailure invoked...");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);

        Reporter.log(String.format("-------------Result: %s-------------", tr.getMethod().getMethodName()), true);
        System.out.println("onTestSkipped invoked...");

    }

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);

         System.out.println("onStart invoked...");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        System.out.println("onFinish invoked...");
    }

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);

        Reporter.log(String.format("-------------Run: %s.%s---------------", result.getTestClass().getName(),
                result.getMethod().getMethodName()), true);
        Reporter.log(String.format("Case description: %s, case priority: %s", result.getMethod().getDescription(),
                result.getMethod().getPriority()),true);
        System.out.println("onTestStart invoked...");
    }

    @Override
    //每次方法失败但是已经使用successPercentage进行注释时调用，并且此失败仍保留在请求的成功百分比之内。
    public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
        super.onTestFailedButWithinSuccessPercentage(tr);
        String result = "FailedButWithinSuccessPercentage";
        Reporter.log(String.format("-------------Result: %s-------------", result), true);

    }

}
