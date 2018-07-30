package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》第1章 Java多线程技能
 * 1.9 yield方法
 * Page： 42
 * yield()方法的作用是广袤当前的CPU资源，将它让给其他的任务去占用CPU执行时间。
 * 但放弃的时间不确定，有可能刚刚放弃，马上以获得CPU时间片。
 *
 * Author:wedo
 * Time:2018-07-24 07:07:11
 */
public class ThreadYieldTest {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 6000000; i++) {
            Thread.yield();
            count = count + (i + 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时： " + (endTime - beginTime) + " 毫秒");
    }
}
