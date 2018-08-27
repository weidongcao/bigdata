package com.book.well_grounded;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 《Java程序员的修炼之道》第2章 新I/O
 *  2.3.2 遍历目录树
 *  Page：27
 *  Java7支持整个目录树的遍历。
 *  也就是说你可以很容易地招录目录树中的文件，在子目录中查找，并对它们执行操作
 * 下面的例子列出了子目录下的所有java源码文件
 *
 * Created by Cao Wei Dong on 2018-08-26 18:27.
 */
public class PathList {
    public static void main(String[] args) throws IOException {
        Path startPath = Paths.get(new File("").getAbsolutePath());
        /*
         * 整个过程从调用 Files.walkFileTree方法开始。这里的关键是FindJavaVisitor，
         * 该类扩展了FileVisitor的默认实现类SimpleFileVisitor。你想让SimpleFileVisitor来
         * 完成大部分 工作，比如遍历目录。可实际上你唯一要做的就是重写visitFile(Path, BasicFileAttributes)
         * 方法，在这个方法中你也只需要写些代码来判断文件名是否以.java结尾，如果确实是，就在stdout中输出。
         *
         * 其他用例包括递归移动、复制、删除或者修改文件。在大多数应用场景中，你只需要扩展SimpleFileVisitor。
         * 但如果你想实现自己的FileVisitor，API也很灵活。
         *
         * 为了确保递归等操作的安全性，walkFileTree方法不会自动跟随符号链接。如果你确实需要跟随符号链接，就需要
         * 检查那个属性并执行相应的操作。
         */
        Files.walkFileTree(startPath, new FindJavaVisitor());
    }


    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(".java")) {
                System.out.println(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

