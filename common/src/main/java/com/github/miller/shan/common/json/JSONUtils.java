package com.github.miller.shan.common.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Project: common
 * @Author: Miller
 * @Time: 2020-5-20 14:42:00
 * @Email: miller.shan.dd@gmail.com;miller.shan@aliyun.com
 * @Description:
 **/
public class JSONUtils {
    public static Integer getResponseCode(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        Integer code = jsonObject.getInteger("code");
        if (null != code) {
            return code;
        } else {
            return null;
        }
    }

    public static String getResponseMessage(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        String message = jsonObject.getString("message");
        return message;
    }
}
