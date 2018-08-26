package com.book.well_grounded;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 《Java程序员修炼之道》第2章新I/O
 * 2.2.2 从Path中获取信息
 * Page：24
 * 在NIO.2的文件I/O中，Path是必须掌握的关键类之一。Path通常代表文件系统中的位置，
 * 比如C:\workspace\java7developer(Windows文件系统中的目录)或/usr/bin/zip(*nix文件系统中
 * zip程序的位置)。你能理解如何创建和处理路径，就能浏览任何类型的文件系统，包括zip归档文件系统。
 *
 * NIO.2中的Path是一个抽象构造。你所创建和处理的Path可以不马上绑定到对应的物理位置上。尽管看起来
 * 奇怪，但是有时确实需要如此。
 * Path类中有一组方法可以返回你正在处理的路径的相关信息。
 * Author：wedo
 * Time:2018-08-26 16:49:07
 */
public class ListingPathTest {
    public static void main(String[] args) throws IOException {
        /*
         * 在创建了指定目录的Path之后，你可以看一下组成Path的元素个数，在此例中就是上当的数量。
         * 相对其父目录和根目录，Path的位置会更有用。也可以通过指定起始和终止的索引来挑出子路径。
         * 在本例中是从Path的根(0)到其第二个元素(2)之间的子路径。
         */
        Path listing = Paths.get("C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\javac.exe");
        System.out.println("File Name [" + listing.getFileName() + "]");
        System.out.println("Number of Name Elements in the Path [" + listing.getNameCount() + "]");
        System.out.println("Parent Path [" + listing.getParent() + "]");
        System.out.println("Root of Path [" + listing.getParent() + "]");
        System.out.println("Subpath from Root, 2 elements deep [" + listing.subpath(0,2) + "]");

        //normalize()方法可以去掉Path中的冗余信息，比如说软链接，相对路径等
        Path lpt = Paths.get("./src/main/java/com/book/well_grounded/ListingPathTest.java").normalize();
        System.out.println("File's absulate path is [" + lpt.getParent() + "]");

        //toRealPath()方法也很有效，它整合了toAbsolutePath()和normalize()两个方法的功能，不能检测并跟随符号链接
        //还是回到Linux/Unix系统中的日志文件那个例子，在/usr/logs目录下有个日志文件log1.txt，而这个目录实际上是指向
        ///application/logs的软链接，通过调用 toRealPath()，你能得到表示/application/logs/log1.txt的真正Path。
        Path realPath = Paths.get("./src/main/java/com/book/well_grounded/ListingPathTest.java").toRealPath();
        System.out.println("File's absulate path is [" + realPath.getParent() + "]");

    }
}
