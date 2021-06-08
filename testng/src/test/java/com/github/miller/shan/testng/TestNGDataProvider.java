package com.github.miller.shan.testng;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 16:39
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: <p>参数化。支持复杂数据类型，或者外部数据。要求返回一个对象数组。
 * 1. @DataProvider 执行时机: @DataProvider 只会执行一次。当 @Test 方法将被执行之前，TestNG会判断此 @Test 注解上是否有属性dataProvider，
 * 如果有，则会先去执行被引用的 @DataProvider 方法，并且将 @DataProvider 标记为已执行，下次运行 @Test方法时，则不会继续在执行 @DataProvider。
 * 需要注意的是 @DataProvider 注解不会影响 @BeforeMethod 和 @AfterMethod 的执行时机。
 * 也就是说每次执行 @Test 方法 @BeforeMethod 和 @AfterMethod 都会被执行。
 * 2. @DataProvider 返回的是一个二维数组,而 @Test 方法的参数接受的是一个 Object 类型数组, 参数真正的类型是由 @DataProvider 二维数组内容决定的。
 * 每次 @Test方法会遍历 @DataProvider 数组中的所有内容，数组中索引有多少对象 @Test 方法就会执行多少次。
 * 3. 数据提供者本身还可以接受两个类型的参数：Method 和 ITestContext.TestNG在调用数据提供者之前会设置这两个参数.
 * 4. 为什么要返回二维数组？首先数据参数化肯定要满足两点，第一需要循环读取数据，第二 @Test 方法需要接受任意的方法参数。所以用二维数组是比较合适的，
 * 第一维度用于循环读取数据，第二维度用于传递任意的参数给测试方法。
 * 5. 数据提供者可以被定义在父类中，这样子类可以方便的复用。
 * </p>
 **/
public class TestNGDataProvider {

    /**
     * 数据提供者。这里的数据也可以是由三方提供读取出来，这里我只是把数据通过对象来封装起来了。
     * 比如也可以从Excel循环读取数据然后将数据保存到二位数组中。
     */
    @DataProvider(name = "dataProviderBean")
    public Object[][] primeNumbers(Method method, ITestContext iTestContext) {
        System.out.println("@DataProvider只执行一次，一次性把数据读取到内存中");
        Object[][] objects = {
                {new DataProviderBean(1, 2, 3)},
                {new DataProviderBean(2, 2, 4)}};
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
        return objects;
    }

    @Test(dataProvider = "dataProviderBean")
    public void testAdd(DataProviderBean dataProviderBean) {
        System.out.println("@Test方法会遍历数据提供者dataProvider中的所有数据");
        Calculator calculator = new Calculator();
        int result = calculator.add(dataProviderBean.getNumber1(), dataProviderBean.getNumber2());
        Assert.assertEquals(result, (int) dataProviderBean.getExpect(), "计算出错");
    }
}
