package com.book.thread.visit;

/**
 * 《Java多线程编程核心技术》第2章 Java多线程技能
 * 2.2.9 静态同步synchronized方法与synchronized(class)代码块
 * Page:97
 * 从运行结果上看，将synchronized关键字加到static方法上与不加效果是一样的。
 * 但是不是有本质的不同的，synchronized关键字加到static静态方法上是给class类上锁，
 * 而synchronized关键字加到非static静态方法上是给对象上锁。
 *
 * Author：wedo
 * Time: 2018-07-27 07:02:51
 */
public class ThreadSyncStatic {
    synchronized public static void printA(){
        try{
            System.out.println("线程名称为：" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "进入printA");
            Thread.sleep(3000);
            System.out.println("线程名称为：" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "离开printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public static void printB(){
        System.out.println("线程名称为：" + Thread.currentThread().getName()
                + "在" + System.currentTimeMillis() + "进入printB");
        System.out.println("线程名称为：" + Thread.currentThread().getName()
                + "在" + System.currentTimeMillis() + "离开printB");
    }

    synchronized public void printC() {
        System.out.println("线程名称为：" + Thread.currentThread().getName()
                + "在" + System.currentTimeMillis() + "进入printC");
        System.out.println("线程名称为：" + Thread.currentThread().getName()
                + "在" + System.currentTimeMillis() + "离开printC");

    }

    public static void main(String[] args) {
        ThreadSyncStatic obj = new ThreadSyncStatic();
        Thread ta = new Thread() {
            @Override
            public void run() {
                super.run();
                printA();
            }
        };
        ta.setName("A");
        ta.start();

        Thread tb = new Thread() {
            @Override
            public void run() {
                super.run();
                printB();
            }
        };
        tb.setName("B");
        tb.start();

        Thread tc = new Thread() {
            @Override
            public void run() {
                super.run();
                obj.printC();
            }
        };
        tc.setName("C");
        tc.start();

    }
}
