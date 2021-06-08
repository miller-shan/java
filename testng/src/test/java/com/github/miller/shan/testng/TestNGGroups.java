package com.github.miller.shan.testng;

import org.testng.annotations.*;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 14:56
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: TestNG组运行的生命周期。
 * <p>
 * @BeforeSuite->@BeforeTest->@BeforeClass->@BeforeGroups{@BeforeMethod->@Test->@AfterMethod}->@AfterGroups->
 *                            @AfterClass->@AfterTest->@AfterSuite 其中{}内的与多少个@Test，就循环执行多少次。
 * </p>
 **/
public class TestNGGroups {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("afterTest");
    }


    @BeforeClass
    public void beforeClass() {
        System.out.println("beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("afterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("afterMethod");
    }

    /**
     * 此方法保证在调用属于这些组中的任何一个的第一个测试方法之前运行此方法.
     * BeforeGroups会在@Test方法之前先运行此注解，与@BeforeClass一样只运行一次。
     */
    @BeforeGroups(value = "miller")
    public void beforeGroups() {
        System.out.println("beforeGroups");
    }

    @AfterGroups(value = "miller")
    public void afterGroups() {
        System.out.println("afterGroups");
    }

    @Test
    public void test1() {
        System.out.println("@test1");
    }

    @Test(groups = "miller")
    public void test2() {
        System.out.println("@test2");
    }

    @Test(groups = "miller")
    public void test3() {
        System.out.println("@test3");
    }

    public void ff() {
        System.out.println("nothing");
    }
}
