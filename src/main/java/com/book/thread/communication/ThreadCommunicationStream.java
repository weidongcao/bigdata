package com.book.thread.communication;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 《Java多线程编程核心技术》第3章 线程间通信
 * 3.1.12 通过管道进行线程间通信：字节流
 * Page：171
 * 在Java语言中提供了各种各样的输入/输出流Stream，使我们能够很方便地对数据
 * 进行操作，其中管道流(pipeStream)是一种特殊的流，用于在不同线程间直接传送
 * 数据。一个线程发送数据到输出管道，另一个线程从输入管道中读数据。通过使用
 * 管道，实现不同线程间的通信，而无须借助于类似临时文件之类的东西。
 * Author：wedo
 * Time：2018-07-31 07:56:20
 */
public class ThreadCommunicationStream {
    private void writeMethod(PipedOutputStream out) {
        try{
            System.out.println("Stream Write:");
            for (int i = 0; i < 300; i++) {
                String str = "" + (i + 1);
                out.write(str.getBytes());
                System.out.print(str);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readMethod(PipedInputStream input) {
        try{
            System.out.println("Stream Read:");
            byte[] arr = new byte[20];
            int readLength = input.read(arr);
            while (readLength != -1) {
                String str = new String(arr, 0, readLength);
                System.out.print(str);
                readLength = input.read(arr);
            }
            System.out.println();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadCommunicationStream c1 = new ThreadCommunicationStream();
        ThreadCommunicationStream c2 = new ThreadCommunicationStream();

        PipedInputStream input = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();

        out.connect(input);

        Thread threadRead = new Thread() {
            @Override
            public void run() {
                super.run();
                c1.readMethod(input);
            }
        };
        Thread threadWrite = new Thread() {
            @Override
            public void run() {
                super.run();
                c2.writeMethod(out);
            }
        };
        threadRead.start();
        Thread.sleep(2000);

        threadWrite.start();
    }
}
