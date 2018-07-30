package com.book.thread.visit;

/**
 * 《Java多线程编程核心技术》第2章 Java多线程技能
 * 2.2.10 数据类型String的常量池特性
 * Page:103
 * JVM具有String常量池缓存的功能，将synchronized(String)同步块与String联合使用时，
 * 会因此带来一些例外
 * Author：wedo
 * Time: 2018-07-27 07:02:51
 */
public class ThreadSyncString {
    public static void print(String str) {
        try{
            synchronized(str){
                while(true){
                    System.out.println(Thread.currentThread().getName() + " --> " + System.currentTimeMillis());
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadSyncString obj = new ThreadSyncString();
        Thread ta = new Thread() {
            @Override
            public void run() {
                super.run();
                obj.print("AA");
            }
        };
        ta.setName("ThreadA");
        ta.start();
        Thread tb = new Thread() {
            @Override
            public void run() {
                super.run();
                obj.print("AA");
            }
        };
        tb.setName("ThreadB");
        tb.start();
    }
}
