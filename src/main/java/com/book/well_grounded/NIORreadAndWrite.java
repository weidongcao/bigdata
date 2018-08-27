package com.book.well_grounded;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * 《Java程序员的修炼之道》第2章 新I/O
 * 2.4.4 快速读写数据
 * Java 7可以直接用带缓冲区的读取器和写入器或输入输出流打开文件。
 * 读写文件的。
 * Created by Cao Wei Dong on 2018-08-28 07:26.
 */
public class NIORreadAndWrite {
    public static void main(String[] args) throws IOException {
//        NioReader();
        NioWriter();
    }

    //下面的代码演示了Java7如何用Files.newBufferedReader方法
    private static void NioReader() {
        Path path = Paths.get("D:\\Module\\Desktop\\hosts");
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //下面的代码演示了Java7如何用Files.newBufferedWriter方法
    private static void NioWriter() throws IOException {
        Path path = Paths.get("D:\\Module\\Desktop\\test");
        if (Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
            Files.createFile(path);
        }
        //注意StandardOpenOption.WRITE选项的使用，这是可以添加的几个OpenOption变参之一。
        // 它可以确保写入的文件有正确的访问许可。其他常用的文件打开选项还有READ和APPEND
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
            writer.write("Hello World!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
