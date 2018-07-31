package com.book.thread.communication;

/**
 * 《Java多线程编程核心技术》第3章 线程间通信
 * 3.2.2 用join()方法来解决
 * Page：181
 * 很多情况下，主线程创建并启动了子线程，如果子线程中要进行大师的故里运算，
 * 主线程往往将早于子线程结束之前结束。这时如果主线程想等待子线程执行完成
 * 之后再结束就要用到join()方法了。
 * join()方法的作用是使所属的线程对象x正常执行run()方法中的任务，而使当
 * 前线程z进行无限期的阻塞，等待线程x销毁后再继续执行线程z后面的代码。
 * 方法join具有使线程排除运行的作用，有些类似同步的运行效果。join与
 * synchronized的区别是：join在内部使用wait()方法进行等待。而synchronized
 * 关键字使用的是"对象监视器"原理做为同步
 * Author：wedo
 * Time:2018-08-01 06:37:08
 */
public class ThreadJoin {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    int sec = (int) (Math.random() * 10000);
                    System.out.println("sec = " + sec);
                    Thread.sleep(sec);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        try{
            thread.start();
            thread.join();
            System.out.println("我想当thread对象执行完毕后再执行，我做到了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
