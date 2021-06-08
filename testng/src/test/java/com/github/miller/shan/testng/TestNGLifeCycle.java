package com.github.miller.shan.testng;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 14:56
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: TestNG的生命周期。
 * <p>
 * 运行顺序
 * @BeforeSuite->@BeforeTest->@BeforeClass->{@BeforeMethod->@Test->@AfterMethod}-> @AfterClass->@AfterTest->@AfterSuite
 * 其中{}内的与多少个@Test，就循环执行多少次。
 * </p>
 **/
public class TestNGLifeCycle {
    @BeforeSuite
    public void beforeSuite() {
        System.out.print("beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.print("afterSuite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.print("beforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.print("afterTest");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.print("beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.print("afterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.print("beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.print("afterMethod");
    }

    @Test
    public void test1() {
        System.out.print("@test1");
    }

    @Test
    public void test2() {
        System.out.print("@test2");
    }

    public void ff() {
        System.out.print("nothing");
    }

    @DataProvider(name = "dataProviderBean")
    public Object[][] primeNumbers() {
        System.out.println("DataProvider只执行一次，一次性把数据读取到内存中");
        Object[][] objects = {
                {new DataProviderBean(1, 2, 3)},
                {new DataProviderBean(2, 2, 4)}};
        System.out.println(objects.length);
        System.out.println(objects);
        return objects;
    }

    @Test(dataProvider = "dataProviderBean")
    public void testAdd(DataProviderBean dataProviderBean) {
        System.out.println(dataProviderBean);
        System.out.println("@Test方法每次执行的都时候都会从数据提供者获取数据，因为数据提供者是一个静态的方法，所以只会初始化一次");
        Calculator calculator = new Calculator();
        int result = calculator.add(dataProviderBean.getNumber1(), dataProviderBean.getNumber2());
        Assert.assertEquals(result, (int) dataProviderBean.getExpect(), "计算出错");
    }
}
