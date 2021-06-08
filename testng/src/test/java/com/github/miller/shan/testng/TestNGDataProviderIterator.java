package com.github.miller.shan.testng;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-30 17:52
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 使用延迟加载方法进行数据的读取.<br />
 * 执行顺序：
 * 1. TestNG先@Test注解，判断上面是否有属性 dataProvider ，如果由则会先去调用 @DataProvider 注解的方法。
 * 2. 实例化 ExcelDataProvider() 类的static成员变量、普通成员变量后调用构造方法。
 * 3. 因为ExcelDataProvider类实现了Iterator接口所以会自动调用 hasNext() 方法，判断是否有下一个元素，如果返回true则继续调用 next()方法。
 * 4. 循环步骤3，读取完所有数据。
 * 5. 运行@Test方法，从 Iterator<Object[]> 中取出DataProvider注解方法获取的所有数据，每个元素是一个数组，并且将元素的类型赋值给方法的形参。
 * <br/>
 * 为什么可以减少内存消耗：因为步骤3在循环调用获取数据的时候，是每次读取读取的，你可以在构造方法里面建立连接，然后在next()方法调用之后释放
 * 不需要的内存，这样就相对于一次性读取所有数据会减少内存消耗。
 **/
public class TestNGDataProviderIterator {
    @Test(dataProvider = "testData")
    public void testDataProviderIterator(Map<String, String> data) {
        System.out.println(data.get("USERNAME") + ":" + data.get("PASSWORD"));
    }

    /**
     * 测试数据提供者.返回一个Iterator<Object[]>对象
     */
    @DataProvider(name = "testData")
    public Iterator<Object[]> dataForTestMethod() {
        System.out.println("不管数据有多少，DataProvider注解的方法都只执行一次");
        String filePath = "home.xls";
        String sheetName = "001";
        //将模块名称和用例的编号传给 ExcelDataProvider ，然后进行读取excel数据
        return new ExcelDataProvider(filePath, sheetName);
    }
}
