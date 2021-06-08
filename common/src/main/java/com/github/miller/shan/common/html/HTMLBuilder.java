package com.github.miller.shan.common.html;

/**
 * @Project: common
 * @Author: Miller
 * @Time: 2020-5-20 14:42:00
 * @Email: miller.shan.dd@gmail.com;miller.shan@aliyun.com
 * @description: 生成HTML格式的表格
 * Uses:
 * HTMLBuilder htmlBuilder = new HTMLBuilder();
 * String table = new HTMLTable()
 * .addTable("这是表的标题", true, 3, 3)
 * .addTableHeader("1H", "2H", "3H")
 * .addRowValues(new String[]{"1", "2", "3"})
 * .addRowValues(new String[]{"4", "5", "6"})
 * .addRowValues(new String[]{"9", "8", "7"}).buildTable();
 * String table2 = table;
 * String result = htmlBuilder.buildHtml(new String[]{"我是body1</br>", table, table2});
 * System.out.println(result);
 */

public class HTMLBuilder {
    public static String HTML_START = "<html>";
    public static String HTML_END = "</html>";
    public static String HTML_HEAD_START = "<style type=\"text/css\">\n" +
            "table{  \n" +
            "border: 1px solid #000000; \n" +
            "margin: 0px;  \n" +
            "padding: 0px;  \n" +
            "width: 100%;  \n" +
            "border-collapse: collapse;  \n" +
            "table-layout: fixed;  /*不添加此样式，会全部显示    */\n" +
            "} \n" +
            "table td{  \n" +
            "border: 1px solid #000000;  \n" +
            "text-overflow: ellipsis;  /* 加上，显示省略号*/\n" +
            "white-space: nowrap;\n" +
            "overflow: hidden;\n" +
            "}\n" +
            "table td:hover { /* 鼠标滑过  显示隐藏的内容  伴有横向的滚动条 */\n" +
            "overflow:auto; \n" +
            "text-overflow:clip; \n" +
            "}\n" +
            "</style>\n" +
            "<script type=\"text/javascript\">\n" +
            "function titleAllTd() {\n" +
            "$(\"#ChaKanGrid tr td\").each(function () {\n" +
            "$(this).attr(\"title\", $(this).text());\n" +
            "$(this).css(\"cursor\", 'pointer');\n" +
            "});\n" +
            "}\n" +
            "</script>";
    public static String HTML_HEAD_END = "</head>";
    public static String HTML_BODY_START = "<body>";
    public static String HTML_BODY_END = "</body>";

    private StringBuilder html;

    public HTMLBuilder() {
        html = new StringBuilder();
        html.append(HTML_START);
        html.append(HTML_HEAD_START);
        html.append(HTML_HEAD_END);
    }

    public String buildHtml(String... bodes) {
        html.append(HTML_BODY_START);
        for (String body : bodes) {
            if (null != body) {
                html.append(body);
            }
        }
        html.append(HTML_BODY_END);
        html.append(HTML_END);
        return html.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HTMLBuilder htmlBuilder = new HTMLBuilder();
        String table = new HTMLTable()
                .addTable("这是表的标题", true, 3, 3)
                .addTableHeader("1H", "2H", "3H")
                .addRowValues(new String[]{"1", "2", "3"})
                .addRowValues(new String[]{"4", "5", "6"})
                .addRowValues(new String[]{"9", "8", "7"}).buildTable();

        String table2 = table;

        String result = htmlBuilder.buildHtml(new String[]{"我是body1</br>", table, table2});
        System.out.println(result);
    }
}

