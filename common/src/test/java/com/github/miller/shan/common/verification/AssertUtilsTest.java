package com.github.miller.shan.common.verification;


import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-22 16:22
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description:
 **/
public class AssertUtilsTest {
    @Test
    public void testIsString() {
        Boolean result = AssertUtils.isString("aaa");
        Assert.isTrue(result, "不是一个字符串");
    }

    @Test
    public void testIsNumber() {
        Boolean result = AssertUtils.isInteger("-1.1");
        System.out.println(result);
    }

    @Test
    public void testIsNumeric() {
        Boolean result = AssertUtils.isNumeric("-11");
        System.out.println(result);
    }

    @Test
    public void testIsFloatOrDouble() {
        Boolean result = AssertUtils.isFloatOrDouble("12345.-123451123");
        System.out.println(result);
    }

    @Test
    public void testIsIntegerOrFloatOrDouble() {
        Boolean result = AssertUtils.isIntegerOrFloatOrDouble("12345123451123");
        System.out.println(result);
        result = AssertUtils.isIntegerOrFloatOrDouble("12345.123451123");
        System.out.println(result);
        result = AssertUtils.isIntegerOrFloatOrDouble("-12345.123451123");
        System.out.println(result);
    }

    @Test
    public void testIsMACAddress() {
        Boolean macAddress = AssertUtils.isMACAddress("1C-1B-0D-60-F4-1e");
        Assert.isTrue(macAddress, "不合法的mac地址");
    }

    @Test
    public void testIsIPAddress() {
        Boolean ipAddress = AssertUtils.isIPAddress("192.168.0.1");
        Assert.isTrue(ipAddress, "不合法的IP地址");
    }

    @Test
    public void testIsDate() {
        Boolean isDate = AssertUtils.isDate("2020-05-14 18:05:43");
        Assert.isTrue(isDate, "不合法的日期");
    }
}
