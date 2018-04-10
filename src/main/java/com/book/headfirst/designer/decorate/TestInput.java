package com.book.headfirst.designer.decorate;

import java.io.*;

/**
 * 测试自己写的Java I/O装饰者
 *
 * Time: 2018-04-10 05:53:56
 * Author: Weidong Cao
 * Created by Cao Wei Dong on 2018-04-10.
 */
public class TestInput {
    public static void main(String[] args) {
        int c;
        try {
            InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("C:\\Windows\\System32\\drivers\\etc\\hosts")));
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
