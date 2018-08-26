package com.book.well_grounded;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 《Java程序员的修炼之道》第2章 新I/O
 * 2.2.4 转换Path
 * Page：25
 * 转换路径最多的是工具软件。比如你可能需要比较文件之间的关系，以便了解源码目录树的结构是否
 * 符合编码规范。或者在Shell脚本中执行程序时可能会传入一些Path参数，并需要把这些参数变成
 * 有意义的Path。在NIO.2里可以很容易地合并Path，在两个Path中再创建Path或对Path进行比较。
 * <p>
 * 要取得两个Path之间的路径，可以用relativize(Path)方法，现在的代码计算了从日志目录到配
 * 置目录之间的路径 。
 * Author: wedo
 * Time: 2018-08-26 17:29:49
 */
public class PathMerge {
    public static void main(String[] args) throws IOException {
        Path prefix = Paths.get("D:\\Workspace\\Code\\bigdata");
        Path complatePath = prefix.resolve("src/main/java/com/book/well_grounded/ListingPathTest.java");
        System.out.println("complatePath's Parent path is [" + complatePath.toAbsolutePath() + "]");

        //要取得两个Path之间的路径，可以用relativize(Path)方法，现在的代码计算了从日志目录到配
        //置目录之间的路径 。
        Path parentPath = complatePath.toAbsolutePath().getParent();
        Path diffPath = prefix.relativize(parentPath);
        System.out.println("diff Path :[" + diffPath + "]");



    }
}
