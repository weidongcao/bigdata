package com.j2se.string;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Cao Wei Dong on 2017-05-15.
 */
public class DangWei {
    public static void main(String[] args) throws IOException {

        func2();
    }

    public static void func2() throws IOException {
        FileReader rf = new FileReader("D:\\Module\\Desktop\\cdh.txt");
        BufferedReader br = new BufferedReader(rf);

        String s;
        while ( (s = br.readLine() ) != null) {
            System.out.println(s);
        }
    }

    public static void func1() throws IOException {
        File file = new File("D:\\Module\\Desktop\\cdh.txt");
        System.out.println(FileUtils.readFileToString(file));
    }


}
