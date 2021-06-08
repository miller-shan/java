package com.github.miller.shan.testng;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.Assert;

/**
 * @Project: java
 * @Author: Miller
 * @Time: 2020-07-10 14:30
 * @Email: miller.shan@aliyun.com;miller.shan.dd@gmail.com
 * @Description: 读取Excel数据<br>
 * 说明：Excel第一行为Map键值<br>
 */
public class ExcelDataProvider implements Iterator<Object[]> {

    private Workbook book = null;
    private Sheet sheet = null;
    private int rowNum = 0;
    private int currentRowNo = 0;
    private int columnNum = 0;
    private String[] columnName;
    private String path = null;
    private InputStream inputStream = null;
    public static Logger logger = Logger.getLogger(ExcelDataProvider.class.getName());

    public ExcelDataProvider(String filePath, String sheetName) {

        try {
            //文件路径
            path = this.getClass().getClassLoader().getResource(filePath).getPath();
            inputStream = new FileInputStream(path);

            book = Workbook.getWorkbook(inputStream);
            sheet = book.getSheet(sheetName);
            rowNum = sheet.getRows(); // 获得该sheet的 所有行
            Cell[] cell = sheet.getRow(0);// 获得第一行的所有单元格
            columnNum = cell.length; // 单元格的个数 值 赋给 列数
            columnName = new String[cell.length];// 开辟 列名的大小

            for (int i = 0; i < cell.length; i++) {
                columnName[i] = cell[i].getContents(); // 第一行的值
            }
            this.currentRowNo++;
        } catch (FileNotFoundException e) {
            logger.info("没有找到指定的文件：" + "[" + path + "]");
            Assert.fail("没有找到指定的文件：" + "[" + path + "]");
        } catch (Exception e) {
            logger.info("不能读取文件： [" + path + "]" + e);
            Assert.fail("不能读取文件： [" + path + "]");
        }
    }

    /**
     * 是否还有下个内容
     */
    @Override
    public boolean hasNext() {
        if (this.rowNum == 0 || this.currentRowNo >= this.rowNum) {
            try {
                inputStream.close();
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            // sheet下一行内容为空判定结束
            if ((sheet.getRow(currentRowNo))[0].getContents().equals(""))
                return false;
            return true;
        }
    }

    /**
     * 返回内容
     */
    @Override
    public Object[] next() {
        Cell[] c = sheet.getRow(this.currentRowNo);
        // 将数据封装成Map存放到数据中
        Map<String, String> data = new HashMap();

        for (int i = 0; i < this.columnNum; i++) {

            String temp = "";

            try {
                temp = c[i].getContents();
            } catch (ArrayIndexOutOfBoundsException ex) {
                temp = "";
            }
            data.put(this.columnName[i], temp);
        }
        Object object[] = new Object[1];
        object[0] = data;
        this.currentRowNo++;
        return object;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove unsupported.");
    }
}

