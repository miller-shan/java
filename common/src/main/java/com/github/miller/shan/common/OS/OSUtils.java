package com.github.miller.shan.common.OS;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: common
 * @Author: Miller
 * @Time: 2020-5-20 14:42:00
 * @Email: miller.shan.dd@gmail.com;miller.shan@aliyun.com
 * @Description: 操作系统交互工具
 **/
@Slf4j
public class OSUtils {
    /**
     * 根据匹配的进程名称，杀掉进程
     *
     * @param processNamePrefix 进程名前缀
     */
    public static void killProcessByName(String processNamePrefix) {

        if (processNamePrefix == null) {
            throw new NullPointerException("The prefix is null,please check it!!");
        }
        // 声明文件读取流
        BufferedReader out = null;
        BufferedReader br = null;
        try {

            // 创建系统进程
            ProcessBuilder pb = new ProcessBuilder("tasklist");
            Process p = pb.start();
            // 读取进程信息
            out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));
            br = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));

            // 创建集合 存放 进程+pid
            List<String> list = new ArrayList<>();
            // 读取
            String ostr;
            while ((ostr = out.readLine()) != null) {
                // 将读取的进程信息存入集合
                list.add(ostr);
            }

            // 遍历所有进程
            for (int i = 3; i < list.size(); i++) {
                // 必须写死,截取长度,因为是固定的
                // 进程名
                String process = list.get(i).substring(0, 25).trim();
                // 进程号
                String pid = list.get(i).substring(25, 35).trim();
                // 匹配指定的进程名,若匹配到,则立即杀死
                if (process.startsWith(processNamePrefix)) {
                    Runtime.getRuntime().exec("taskkill /F /PID " + pid);
                }
            }

            // 若有错误信息 即打印日志
            String str = br.readLine();
            if (str != null) {
                log.error(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关流
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 执行系统的shell、bat、cmd命令
     *
     * @param command 命令
     * @return 执行结果
     */
    public static String executeCommand(String command) {
        //执行命令
        String osName = System.getProperty("os.name");
        String[] cmd = new String[3];
        if (osName.equals("Windows NT") || osName.equalsIgnoreCase("Windows 10")
                || osName.equalsIgnoreCase("Windows 7")) {
            cmd[0] = "cmd.exe";
            cmd[1] = "/C";
            cmd[2] = command;
        } else if (osName.equals("Windows 95")) {
            cmd[0] = "command.com";
            cmd[1] = "/C";
            cmd[2] = command;
        } else {
            cmd[0] = "cmd.exe";
            cmd[1] = "/C";
            cmd[2] = command;
        }
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Executing " + cmd[0] + " " + cmd[1] + " " + cmd[2]);

        try {
            Process process = runtime.exec(cmd);
            // 使用exitValue来取得外部命令的返回状态
            Integer exitValue;
            // exitValue()：返回子进程的出口值，值0表示正常终止.非阻塞执行
            // exitValue = process.exitValue();
            // 待外部命令执行完毕的，这个方法会一直阻塞直到外部命令执行结束，然后返回外部命令执行的结果.比如执行notepad.exe之后那么就要等到notepad关闭才会接触阻塞
            exitValue = process.waitFor();
            //取得命令结果的输出流
            InputStream fis = process.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(fis, "gbk");
            //用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            //直到读完为止
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }
            return stringBuffer.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
