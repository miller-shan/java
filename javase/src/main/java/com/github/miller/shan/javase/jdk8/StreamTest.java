package com.github.miller.shan.javase.jdk8;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-28 16:34
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 流，是对数据的操作。不会改变数据源的数据。
 **/
public class StreamTest {

    private List<User> users = Arrays.asList(
            new User("AAA", 11, 111.111),
            new User("BBB", 22, 222.111),
            new User("CCC", 33, 333.111),
            new User("DDD", 44, 444.111),
            new User("EEE", 55, 555.111)
    );

    @Test
    public void testStream() {
        // 第一步：创建流
        Stream<User> stream = users.stream();
        // 第二步：中间操作
        Stream<User> employeeStream = stream.filter((x) -> {
            System.out.println("Steam API 正在操作流！");
            boolean b = x.getAge() > 33;
            return b;
        });
        Stream<User> limit = employeeStream.limit(1);

        // 第三步：终止流。不调用终止操作时中间操作不会生效，这叫做“惰性求值”
        limit.forEach(System.out::println);
    }

}
