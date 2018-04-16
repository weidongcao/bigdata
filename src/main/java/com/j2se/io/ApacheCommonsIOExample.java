package com.j2se.io;

import org.apache.commons.io.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Cao Wei Dong on 2017-05-25.
 */
public class ApacheCommonsIOExample {
    public static final String EXAMPLE_TXT_PATH = "D:\\Workspace\\Code\\com.bigdata\\dangwei.txt";
    public static final String PARENT_DIR = "D:\\Workspace\\Code";

    public static void main(String[] args) throws IOException {
        runExample();
    }

    public static void runExample() throws IOException {
        //FilenameUtils
        System.out.println("绝对路径文件名的路径（带最后的分隔符）: " + FilenameUtils.getFullPath(EXAMPLE_TXT_PATH));
        System.out.println("绝对路径文件名的路径（不带最后的分隔符）: " + FilenameUtils.getFullPathNoEndSeparator(EXAMPLE_TXT_PATH));
        System.out.println("绝对路径文件名的文件名（不带最后的分隔符）: " + FilenameUtils.getName(EXAMPLE_TXT_PATH));
        System.out.println("获取文件后缀: " + FilenameUtils.getExtension(EXAMPLE_TXT_PATH));
        System.out.println("获取文件名不带后缀: " + FilenameUtils.getBaseName(EXAMPLE_TXT_PATH));

        //FileUtils
        File exampleFile = FileUtils.getFile(EXAMPLE_TXT_PATH);
        LineIterator iter = FileUtils.lineIterator(exampleFile);

        System.out.println("开始打印文件内容.....");
        while (iter.hasNext()) {
            System.out.println("file content line = " + iter.next());
        }

        iter.close();

        /**
         * 我们可以检查一个文件是否存在某一个特定的目录下
         */
        File parent = FileUtils.getFile(PARENT_DIR);
        System.out.println("父目录包含exampleTxt 文件: " + FileUtils.directoryContains(parent, exampleFile));

        // IOCase
        String str1 = "This is a new String.";
        String str2 = "This is another new String, yes!";

        System.out.println("判断字符串是否以指定字符串结尾(大小写敏感): " + IOCase.SENSITIVE.checkEndsWith(str1, "string."));
        System.out.println("判断字符串是否以指定字符串结尾(大小写不敏感): " + IOCase.INSENSITIVE.checkEndsWith(str1, "string."));

        System.out.println("判断两个客串是否相等(大小写敏感): " + IOCase.SENSITIVE.checkEquals(str1, str2));

        //FileSystemUtils
        System.out.println("磁盘可用空间(KB): " + FileSystemUtils.freeSpaceKb("C:"));
        System.out.println("磁盘可用空间(MB): " + FileSystemUtils.freeSpaceKb("C:") / 1024);
    }
}
