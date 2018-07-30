package com.book.thread.visit;

/**
 * 《Java多线程编程核心技术》第2章 Java多线程技能
 * 2.2.5 synchronized代码块间的同步性
 * Page:78
 * 在使用同步synchronized(this)代码块时需要注意的是，当一个线程访问object的一个
 * synchronized(this)同步代码块时，其他线程对同一个object中所有其他synchronized(this)
 * 同步代码块的访问将被阻塞，这说明synchonized使用的"对象监视器"是一个
 * Author：wedo
 * Time: 2018-07-26 06:27:53
 */
public class ThreadSyncSync {
    private void methodA() {
        try{
            synchronized(this){
                System.out.println("A begin time=" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A end   time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void methodB() {
        synchronized (this) {
            System.out.println("B begin time=" + System.currentTimeMillis());
            System.out.println("B end   time=" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        ThreadSyncSync obj = new ThreadSyncSync();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                super.run();
                obj.methodA();
            }
        };
        t1.setName("ThreadA");
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                super.run();
                obj.methodB();
            }
        };
        t2.setName("ThreadB");
        t2.start();
    }
}
