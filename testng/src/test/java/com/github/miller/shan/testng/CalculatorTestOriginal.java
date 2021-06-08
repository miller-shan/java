package com.github.miller.shan.testng;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 14:38
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description:
 **/
public class CalculatorTestOriginal {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Integer add = calculator.add(1, 2);
        if (3 == add) {
            System.out.println("success");
        } else {
            System.out.println("fail" + add + "is not 3");
        }
    }
}
