package com.github.miller.shan.javase.jdk8;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-07-15 17:42
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 从匿名内部类到Lambda表达式的转换
 **/
public class LambdaTest {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Multi Thread...");
            }
        };
        runnable.run();
        //使用Lambda。可以发现Lambda就像一段可以传递的代码，有些像是方法的参数传递
        Runnable runnable1 = () -> System.out.println("Hello Lambda...");
        runnable1.run();

    }
}
