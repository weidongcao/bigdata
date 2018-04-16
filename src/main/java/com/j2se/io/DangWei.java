package com.j2se.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cao Wei Dong on 2017-05-20.
 */
public class DangWei {
    public static void main(String[] args) throws IOException {
        //step1 read content from file
        FileReader fr = new FileReader("D:\\Module\\Desktop\\cdh.txt");
        BufferedReader br = new BufferedReader(fr);

        // create handle result file
        File result = new File("dangwei.txt");

        //if the result file exists delete it
        if (result.exists()) {
            //delete
            result.delete();
        }
        result.createNewFile();
        FileWriter fw = new FileWriter(result);

        List<String> list = new ArrayList<>();

        //step2. handle string
        //step3. write string into file
        String line;
        //每次读取文件的一行
        while ((line = br.readLine()) != null) {
            line = line.replaceAll("\\[(.)*\\]\t", "")  //替换[   ]
                    .replaceAll("-(.)*", "")    //-0.11.0-cdh5.6.0.tar.gz	2016-02-24 00:23	156M
                    .replaceAll("[^a-z]", "");   // /;

            //如果经过上面的处理还是包含空格或者为空说明它不是我想要的
            if (line.contains(" ") || "".equals(line)) {
                continue;
            }
//            System.out.println(line);
            //对处理结果进行去重，如果list中已经包含了此此字符串，则过滤掉
            if (list.contains(line) == false) {
                list.add(line);
            }
        }

        //遍历list集合，将去重后的结果写入文件
        for (String s : list) {
//            System.out.println(s);
            fw.write(s + "\r\n");
        }

        //step4. close file io
        try {
            br.close();
            fr.close();
            fw.close();
        } catch (IOException e) {
            br.close();
            fr.close();
            fw.close();
        }finally {
            if (null == br) {
                br.close();
            }
            if (null == fr) {
                fr.close();
            }
            if (null == fw) {
                fw.close();
            }
        }
    }
}
