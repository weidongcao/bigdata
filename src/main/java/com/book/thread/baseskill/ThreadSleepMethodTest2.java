package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》 高洪岩 第1章 Java多线程技能
 * 1.5 sleep()方法
 * Page：21
 * 方法sleep()的作用是在指定的毫秒数内让当前"正在执行的线程"休眠(暂停执行).
 * 这个"正在执行的线程"是指this.currentThread()返回的线程
 *
 * @author wedo
 * Time: 2018-07-15 08:41:34
 */
public class ThreadSleepMethodTest2 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run threadName = " + this.currentThread().getName() + " begin = " + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("run threadName = " + this.currentThread().getName() + " end   = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadSleepMethodTest2 t1 = new ThreadSleepMethodTest2();
        System.out.println("begin = " + System.currentTimeMillis());
        //重点看这里
        t1.start();
        System.out.println("end   = " + System.currentTimeMillis());
    }
}
