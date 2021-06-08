package com.github.miller.shan.testng;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-06-29 17:07
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 数据提供者
 **/

public class DataProviderBean {
    private Integer number1;
    private Integer number2;
    private Integer expect;


    public DataProviderBean() {
    }

    public DataProviderBean(Integer number1, Integer number2, Integer expect) {
        this.number1 = number1;
        this.number2 = number2;
        this.expect = expect;
    }

    public Integer getExpect() {
        return expect;
    }

    public void setExpect(Integer expect) {
        this.expect = expect;
    }

    public Integer getNumber2() {
        return number2;
    }

    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }

    public Integer getNumber1() {
        return number1;
    }

    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    @Override
    public String toString() {
        return "DataProviderBean{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
