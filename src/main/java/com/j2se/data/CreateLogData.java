package com.j2se.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/3.
 */
public class CreateLogData {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        List<String> users = new ArrayList<String>();

        List<String> words = new ArrayList<String>();

        List<String> cities = new ArrayList<>();

        String[] mobile = {"Android", "Android", "Android", "Android", "iphone"};

        String[] version = {"1.0", "1.1", "1.2", "1.5", "2.0"};


        FileReader fr = new FileReader("E:\\Workspaces\\resource\\TestData\\word.txt");
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        while ((line = br.readLine()) != null) {
            if (users.contains(line) == false) {
                users.add(line);
            }
        }

        fr = new FileReader("E:\\Workspaces\\resource\\TestData\\bigdatakey.txt");
        br = new BufferedReader(fr);

        while ((line = br.readLine()) != null) {
            if (words.contains(line) == false) {
                words.add(line);
            }
        }


        fr = new FileReader("E:\\Workspaces\\resource\\TestData\\city.txt");
        br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {

            if (words.contains(line.split("\t")[2]) == false) {
                cities.add(line.split("\t")[2]);
            }
        }


        String output = "E:\\Workspaces\\resource\\TestData\\logs.txt";
        File outputFile = new File(output);

        if (outputFile.getParentFile().exists() == false) {
            if (outputFile.getParentFile().mkdir() == false) {
                System.out.println("创建目标文件所在目录失败");
            }
        }
        if ( outputFile.exists() == true) {
            outputFile.delete();
        }
        outputFile.createNewFile();
        FileWriter fw = new FileWriter(output);
        BufferedWriter bw = new BufferedWriter(fw);

        String temp = "2016-10-";
        String log  = null;

        for (int i = 1; i < 31; i++) {
            String date = temp + i;
            for (int j = 0; j < 1000; j++) {
                log = date + "\t"
                        + users.get(random.nextInt(users.size())) + "\t"
                        + words.get(random.nextInt(60)) + "\t"
                        + cities.get(random.nextInt(16)) + "\t"
                        + mobile[random.nextInt(5)] + "\t"
                        + version[random.nextInt(5)]
                        + "\r\n";
                bw.write(log);
            }
        }
        bw.flush();
        bw.close();
    }
}
