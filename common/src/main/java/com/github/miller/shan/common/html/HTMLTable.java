package com.github.miller.shan.common.html;

/**
 * @Project: common
 * @Author: Miller
 * @Time: 2020-5-20 14:42:00
 * @Email: miller.shan.dd@gmail.com;miller.shan@aliyun.com
 * @Description: 生成HTML的Table
 */
public class HTMLTable {

    private int columns;
    private String TABLE_START_BORDER = "<table border=\"1\" width=\"100%\">";
    private String TABLE_START = "<table>";
    private String TABLE_END = "</table>";
    private String TABLE_HEADER_START = "<th>";
    private String TABLE_HEADER_END = "</th>";
    private String TABLE_ROW_START = "<tr>";
    private String TABLE_ROW_END = "</tr>";
    private String TABLE_COLUMN_START = "<td>";
    private String TABLE_COLUMN_END = "</td>";
    private StringBuilder table = new StringBuilder();
    private Integer lineNumber = 0;

    /**
     * @param tableTitle
     * @param border
     * @param rows
     * @param columns
     */
    public HTMLTable addTable(String tableTitle, boolean border, int rows, int columns) {
        this.columns = columns;
        if (tableTitle != null) {
            table.append("<h2 style=\"text-align: center;\">");
            table.append(tableTitle);
            table.append("</h2>");
        }
        table.append(border ? TABLE_START_BORDER : TABLE_START);
        table.append(TABLE_END);
        return this;
    }


    /**
     * @param heads
     */
    public HTMLTable addTableHeader(String... heads) {
        if (heads.length != columns) {
            System.out.println("Error column length");
        } else {
            int lastIndex = table.lastIndexOf(TABLE_END);
            if (lastIndex > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(TABLE_ROW_START);
                for (String value : heads) {
                    sb.append(TABLE_HEADER_START);
                    sb.append(value);
                    sb.append(TABLE_HEADER_END);
                }
                sb.append(TABLE_ROW_END);
                table.insert(lastIndex, sb.toString());
            }
        }
        return this;
    }


    /**
     * @param rows
     */
    public HTMLTable addRowValues(String... rows) {
        if (rows.length != columns) {
            System.out.println("Error column length");
        } else {
            lineNumber += 1;
            int lastIndex = table.lastIndexOf(TABLE_ROW_END);
            if (lastIndex > 0) {
                int index = lastIndex + TABLE_ROW_END.length();
                StringBuilder sb = new StringBuilder();
                sb.append(TABLE_ROW_START);
                for (String value : rows) {
                    sb.append(TABLE_COLUMN_START);
                    sb.append(value);
                    sb.append(TABLE_COLUMN_END);
                }
                sb.append(TABLE_ROW_END);
                table.insert(index, sb.toString());
            }
        }
        return this;
    }

    /**
     * @return
     */
    public String buildTable() {
        return table.toString();
    }

    public Integer getLineNumber() {
        return lineNumber;
    }
}
