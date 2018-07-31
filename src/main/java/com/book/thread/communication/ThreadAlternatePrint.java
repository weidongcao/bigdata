package com.book.thread.communication;

/**
 * 《Java多线程编程核心技术》第3章 线程间通信
 * 3.1.14 实战：等待/通知之交叉备份
 * 创建20个线程，10个线程是将数据备份到A数据库中，另外10个线程将数据备份到B数据库中，
 * 并且备份A数据库与备份B数据库是交叉进行的。
 * Author：wedo
 * Time：2018-07-31 08:14:55
 */
public class ThreadAlternatePrint {
    volatile private boolean prevIsA = false;
    synchronized public void backupA() {
        try{
            while (prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("★★★★★");
            }
            prevIsA = !prevIsA;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public void backupB() {
        try{
            while (!prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("☆☆☆☆☆");
            }
            prevIsA = !prevIsA;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadAlternatePrint tap = new ThreadAlternatePrint();
        for (int i = 0; i < 20; i++) {
            Thread backupB = new Thread() {
                @Override
                public void run() {
                    super.run();
                    tap.backupB();
                }
            };
            backupB.start();
            Thread backupA = new Thread() {
                @Override
                public void run() {
                    super.run();
                    tap.backupA();
                }
            };
            backupA.start();

        }
    }
}
