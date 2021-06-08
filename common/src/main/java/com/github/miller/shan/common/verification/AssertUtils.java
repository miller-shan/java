package com.github.miller.shan.common.verification;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.regex.Pattern;

/**
 * @Project: common
 * @Author: Miller
 * @Time: 2020-5-20 14:42:00
 * @Email: miller.shan.dd@gmail.com;miller.shan@aliyun.com
 * @Description: 断言工具
 **/
@Slf4j
public class AssertUtils {

    public static Boolean assertData(String assertType, Object actual, Object expect) {
        assertType = assertType.toUpperCase();
        switch (assertType) {
            //equalsIgnoreCase
            case "EQUALSIGNORECASE":
                return assertEqualsIgnoreCase(String.valueOf(actual), String.valueOf(expect));
            //"contains"
            case "CONTAINS":
                return assertContains(String.valueOf(actual), String.valueOf(expect));
            default:
                throw new RuntimeException(assertType + "暂不支持此种校验方式");
        }
    }

    public static Boolean assertEqualsIgnoreCase(String actual, String expect) {
        if (actual.equalsIgnoreCase(expect)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean assertContains(String actual, String expect) {
        if (actual.contains(expect)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean assertGreaterThan(Integer actual, Integer expect) {
        if (actual < expect) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean assertLessThan(Integer actual, Integer expect) {
        if (actual < expect) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isString(String text) {
        if (text instanceof String) {
            log.debug("It is String Type.{}", text);
            return true;
        } else {
            log.debug("It is not String Type.{}", text);
            return false;
        }
    }

    /**
     * 判断一个字符串是否是一个整数。不区分正负数。不能判断小数
     */
    public static Boolean isInteger(String text) {
        //?:0或1个, *:0或多个, +:1或多个
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

        Boolean strResult = pattern.matcher(text).matches();
        if (strResult) {
            log.debug("It is integer.{}", text);
            return true;
        } else {
            log.debug("It is not integer.{}", text);
            return false;
        }
    }

    /**
     * 判读一个字符串是否是一个正整数。
     */
    public static boolean isNumeric(String text) {
        for (int i = text.length(); --i >= 0; ) {
            if (!Character.isDigit(text.charAt(i))) {
                log.debug("It is not positive integer. {}", text);
                return false;
            }
        }
        log.debug("It is positive integer. {}", text);
        return true;
    }

    /**
     * 判断字符串是否是一个小数。不区分正负。
     */
    public static Boolean isFloatOrDouble(String text) {
        if (text.contains(".")) {
            String intValue = text.split("\\.")[0];
            String floatValue = text.split("\\.")[1];
            if (isInteger(intValue) && isNumeric(floatValue)) {
                log.debug("It is float or double.{}", text);
                return true;
            } else {
                log.debug("It is not float or double.{}", text);
                return false;
            }
        } else {
            log.debug("It is not float or double type.{}", text);
            return false;
        }
    }

    /**
     * 判断字符串是否是一个数字类型。不区分正负、不区分小数
     */
    public static Boolean isIntegerOrFloatOrDouble(String text) {
        Boolean integer = isInteger(text);
        Boolean floatOrDouble = isFloatOrDouble(text);
        if (integer || floatOrDouble) {
            log.debug("It is integer or float or double or decimal.{}", text);
            return true;
        } else {
            log.debug("It is not integer or float or double or decimal.{}", text);
            return false;
        }
    }

    /**
     * 判断一个字符串是否是一个合法的MAC地址
     */
    public static Boolean isMACAddress(String text) {
        String trueMacAddress = "([A-Fa-f0-9]{2}-){5}[A-Fa-f0-9]{2}";
        // 这是真正的MAV地址；正则表达式；
        if (text.matches(trueMacAddress)) {
            log.debug("It is MAC address.{}", text);
            return true;
        } else {
            log.debug("It is not MAC address.{}", text);
            return false;
        }
    }

    /**
     * 判断一个字符串是否是一个合法的IP地址
     * 字符串的长度 0.0.0.0 7位 ~ 000.000.000.000 15位
     * 将字符串拆分成四段
     * 检查每段是否都是纯数字
     * 检查每段是否都在0-255之间
     * 以上条件都满足的话返回true
     */
    public static boolean isIPAddress(String text) {
        // 如果长度不符合条件 返回false
        if (text == null || text.length() == 0) {
            log.debug("It is not IP address.{}", text);
            return false;
        }
        String[] parts = text.split("\\.");//因为java doc里已经说明, split的参数是reg, 即正则表达式, 如果用"|"分割, 则需使用"\\|"
        if (parts.length != 4) {
            log.debug("It is not IP address.{}", text);
            return false;//分割开的数组根本就不是4个数字
        }
        for (int i = 0; i < parts.length; i++) {
            try {
                int n = Integer.parseInt(parts[i]);
                if (n < 0 || n > 255) {
                    log.debug("It is not IP address.{}", text);
                    return false;//数字不在正确范围内
                }
            } catch (NumberFormatException e) {
                log.debug("It is not IP address.{}", text);
                return false;//转换数字不正确
            }
        }
        log.debug("It is IP address.{}", text);
        return true;
    }

    /**
     * 判断字符串是否是一个日志格式。目前支持的格式如下：
     * "yyyy-MM-dd", "yyyy年MM月dd日", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
     * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd"
     */
    public static Boolean isDate(String text) {
        String[] parsePatterns = {"yyyy-MM-dd", "yyyy年MM月dd日",
                "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
                "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd"};
        if (text == null) {
            log.debug("It is not date format.{}");
            return false;
        }
        try {
            DateUtils.parseDate(text, parsePatterns);
            log.debug("It is date format.{}", text);
            return true;
        } catch (ParseException e) {
            log.debug("It is not date format.{}", text);
            return false;
        }
    }
}
