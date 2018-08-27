package com.book.well_grounded;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 《Java程序员的修炼之道》第2章 新I/O
 *  2.3.1 在目录中查找文件
 *  Page：26
 *  用模式匹配过滤出项目中所有的.xml文件(不递归)
 * 方法DirectoryStream(Path directory, String pathernMatch)返回一个经过过滤的DirectoryStream
 * 其中包含以.xml结尾的文件。最后输出每个子项
 * 过滤流中用到的模式匹配称为glob模式匹配，它和Perl正则表达式类似，但稍有不同。
 *@author wedo
 * 2018-08-26 18:19:13
 */
public class PathSearch {
    public static void main(String[] args) {
        Path rootPath = Paths.get(new File("").getAbsolutePath());
        System.out.println("root dir = " + rootPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(rootPath, "*.xml")) {
            for (Path path :stream) {
                System.out.println(path.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
