package com.github.miller.shan.common.bean;

import java.util.Map;

/**
 * @Project: common
 * @Author: Miller
 * @Time: 2020-5-20 14:42:00
 * @Email: miller.shan.dd@gmail.com;miller.shan@aliyun.com
 * @Description: 统一返回格式
 **/
public class ResultMessage {
    /**
     * 是否存在错误。成功是返回false, 失败时返回true
     */
    private boolean error;
    /**
     * 返回错误状态码。默认成功0， 失败-1
     */
    private Integer code;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 返回的对象
     */
    private Object data;
    /**
     * 业务自己定义的返回信息
     */
    private Map<String, Object> businessStatus;


    private ResultMessage() {
    }

    public static com.github.miller.shan.common.bean.ResultMessage success() {
        return success(0, "success", null, null);
    }

    public static com.github.miller.shan.common.bean.ResultMessage success(String message) {
        return success(0, message, null, null);
    }

    public static com.github.miller.shan.common.bean.ResultMessage success(Object data) {
        return success(0, "success", data, null);
    }

    public static com.github.miller.shan.common.bean.ResultMessage success(Map<String, Object> businessStatus) {
        return success(0, "success", null, businessStatus);
    }

    public static com.github.miller.shan.common.bean.ResultMessage success(String message, Object data) {
        return success(0, message, data, null);
    }

    public static com.github.miller.shan.common.bean.ResultMessage success(String message, Object data, Map<String, Object> businessStatus) {
        return success(0, message, data, businessStatus);
    }

    public static com.github.miller.shan.common.bean.ResultMessage failed() {
        return failed(-1, "failed", null, null);
    }

    public static com.github.miller.shan.common.bean.ResultMessage failed(String message) {
        return failed(-1, message, null, null);
    }

    public static com.github.miller.shan.common.bean.ResultMessage failed(Object data) {
        return failed(-1, "failed", data, null);
    }

    public static com.github.miller.shan.common.bean.ResultMessage failed(Map<String, Object> businessStatus) {
        return failed(-1, "failed", null, businessStatus);
    }

    public static com.github.miller.shan.common.bean.ResultMessage failed(String message, Object data) {
        return failed(-1, message, data, null);
    }

    public static com.github.miller.shan.common.bean.ResultMessage failed(String message, Object data, Map<String, Object> businessStatus) {
        return failed(-1, message, data, businessStatus);
    }

    public static com.github.miller.shan.common.bean.ResultMessage success(Integer code, String message, Object data, Map<String, Object> businessStatus) {
        com.github.miller.shan.common.bean.ResultMessage resultMessage = new com.github.miller.shan.common.bean.ResultMessage();
        resultMessage.setError(false);
        resultMessage.setCode(code);
        resultMessage.setMessage(message);
        resultMessage.setData(data);
        resultMessage.setBusinessStatus(businessStatus);
        return resultMessage;
    }

    public static com.github.miller.shan.common.bean.ResultMessage failed(Integer code, String message, Object data, Map<String, Object> businessStatus) {
        com.github.miller.shan.common.bean.ResultMessage resultMessage = new com.github.miller.shan.common.bean.ResultMessage();
        resultMessage.setError(true);
        resultMessage.setCode(code);
        resultMessage.setMessage(message);
        resultMessage.setData(data);
        resultMessage.setBusinessStatus(businessStatus);
        return resultMessage;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setBusinessStatus(Map<String, Object> businessStatus) {
        this.businessStatus = businessStatus;
    }

    public boolean isError() {
        return error;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Map<String, Object> getBusinessStatus() {
        return businessStatus;
    }
}
