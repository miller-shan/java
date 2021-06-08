package com.github.miller.shan.javase.basic;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-07-17 11:00
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: static 关键字。
 * https://blog.csdn.net/qq_35868412/article/details/89360250
 **/
public class StaticKeyWord {
    /*
    静态代码块会在类加载之后自动执行一次，而且也只会执行一次。
    静态代码块里面只能用于成员变量的初始化，而不能定义方法
     */
    static {
        System.out.println("hello");
//        public void test(){}
    }
}
