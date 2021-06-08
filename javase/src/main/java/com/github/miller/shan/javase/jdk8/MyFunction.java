package com.github.miller.shan.javase.jdk8;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-28 14:46
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 函数式编程。要求接口中只能有一个抽象方法
 **/
@FunctionalInterface
public interface MyFunction {
    public Integer getValue(Integer num);
}
