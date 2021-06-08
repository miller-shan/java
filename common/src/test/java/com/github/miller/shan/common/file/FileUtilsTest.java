package com.github.miller.shan.common.file;

import org.junit.jupiter.api.Test;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-22 13:53
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description:
 **/
public class FileUtilsTest {
    @Test
    public void testGenerateRandomFileName() {
        String filePath = "pom.xml";

        String s = FileUtils.generateRandomFileName(filePath);
        System.out.println(s);
    }
}
