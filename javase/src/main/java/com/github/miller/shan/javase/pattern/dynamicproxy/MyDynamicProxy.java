package com.github.miller.shan.javase.pattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-07-09 10:30
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 设计模式-动态代理
 **/
public class MyDynamicProxy {
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);

        // 构造代码示例
        Object proxyHello = Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler);
        // 使用这个实例的时候默认会调用InvocationHandler.invoke()方法
//        System.out.println(proxyHello);
        // pattern.dynamicproxy.$Proxy0
        System.out.println(proxyHello.getClass());
        // 调用代理方法
        ((Hello) proxyHello).sayHello();
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Invoking sayHello method...");
        Object result = method.invoke(target, args);
        return result;
    }
}

interface Hello {
    void sayHello();
}

class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("Hello DynamicProxy...");
    }
}
