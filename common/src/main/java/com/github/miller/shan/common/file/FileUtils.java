package com.github.miller.shan.common.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Project: common
 * @Author: Miller
 * @Time: 2020-5-20 14:42:00
 * @Email: miller.shan.dd@gmail.com;miller.shan@aliyun.com
 * @Description: 文件操作类
 **/
@Slf4j
public class FileUtils {

    /**
     * 读取resources目录下的文件路径
     *
     * @param fileName
     * @return
     */
    public static String readResourcesFilePath(String fileName) {
        return FileUtils.class.getClass().getClassLoader().getResource(fileName).getPath();
    }

    /**
     * 读取文件内容
     *
     * @param filePath 文件路径
     * @return 文件内容
     */
    public static String readFile(String filePath) {
        // 通道
        FileChannel fileChannel = null;
        // 缓冲区
        ByteBuffer byteBuffer = null;
        // 存储的内容
        StringBuilder stringBuilder = null;
        try {
            // 建立通道,设置通过为只能读数据
            fileChannel = FileChannel.open(Paths.get(filePath), StandardOpenOption.READ);
            // 分配缓冲区大小
            byteBuffer = ByteBuffer.allocate(1024);
            stringBuilder = new StringBuilder();
            // 将通道中的数据存入缓冲区中
            while (fileChannel.read(byteBuffer) != -1) {
                //切换到读模式。切换到读模式之后position位置又会从0开始读取数据。同时limit位置会改变成你上次写入缓冲区数据的长度
                byteBuffer.flip();
                stringBuilder.append(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                byteBuffer.clear();
            }
            log.debug(FileUtils.class.getName() + ".readFile() {}", new File(filePath).getCanonicalPath());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            log.error("read file error.{}", ioException);
        } finally {
            try {
                fileChannel.close();
                return stringBuilder.toString();
            } catch (IOException ioException) {
                log.error("close file error.{}", ioException);
                ioException.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 文件的复制，使用直接缓冲区(物理内存)
     * 优点：效率高，速度快。
     * 缺点：程序将复制任务直接交给操作系统来管理，什么时候操作系统会进行复制，合适返回结果给程序，这个是由CPU调度来决定的，程序无法控制
     *
     * @param sourceFilePath      源文件
     * @param destinationFilePath 目标路径
     */
    public static void copyFileByMemoryMap(String sourceFilePath, String destinationFilePath) {
        // 使用内存映射文件完成文件的复制
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            // 建立通道
            inputChannel = FileChannel.open(Paths.get(sourceFilePath), StandardOpenOption.READ);
            outputChannel = FileChannel.open(Paths.get(destinationFilePath), StandardOpenOption.READ,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            // JVM内存与物理内存的映射文件
            MappedByteBuffer inputMapMode = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());
            MappedByteBuffer outputMapMode = outputChannel.map(FileChannel.MapMode.READ_WRITE, 0, inputChannel.size());
            // 直接对缓冲区进行读写操作
            byte[] destination = new byte[inputMapMode.limit()];
            inputChannel.read(ByteBuffer.wrap(destination));
            outputMapMode.put(destination);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (null != inputChannel) {
                try {
                    inputChannel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (null != null) {
                try {
                    outputChannel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        log.debug(FileUtils.class.getName() + ".copyFileByMemoryMap() copy:{} to:{}", sourceFilePath, destinationFilePath);
    }

    /**
     * 文件的复制，使用非直接缓冲区(通过JVM来管理内存)
     *
     * @param sourceFilePath      源文件
     * @param destinationFilePath 目标路径
     */
    public static void copyFileByChannel(String sourceFilePath, String destinationFilePath) {

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        //①获取通道
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            fileInputStream = new FileInputStream(sourceFilePath);
            fileOutputStream = new FileOutputStream(destinationFilePath);

            inputChannel = fileInputStream.getChannel();
            outputChannel = fileOutputStream.getChannel();

            //②分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //③将通道中的数据存入缓冲区中
            while (inputChannel.read(buf) != -1) {
                //切换读取数据的模式
                buf.flip();
                //④将缓冲区中的数据写入通道中
                outputChannel.write(buf);
                //清空缓冲区
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputChannel != null) {
                try {
                    outputChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputChannel != null) {
                try {
                    inputChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.debug(FileUtils.class.getName() + ".copyFileByChannel() copy:{} to:{}", sourceFilePath, destinationFilePath);
    }

    /**
     * 生成随机文件名
     *
     * @param filePath 文件路径
     * @return 原文件名_时间戳.原后缀
     */
    public static String generateRandomFileName(String filePath) {

        File file = new File(filePath);
        String fileName = file.getName();
        if (fileName.contains(".")) {
            int i = fileName.lastIndexOf(".");
            String name = fileName.substring(0, i);
            String suffix = fileName.substring(i, fileName.length());
            fileName = name + "_" + System.currentTimeMillis() + suffix;
        } else {
            fileName = fileName + "_" + System.currentTimeMillis();
        }

        filePath = file.getParent() + File.separator + fileName;
        log.debug(FileUtils.class.getName() + ".generateRandomFileName() {}", filePath);
        return filePath;
    }

}
