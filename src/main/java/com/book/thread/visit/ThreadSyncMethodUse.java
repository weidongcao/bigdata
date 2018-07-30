package com.book.thread.visit;

/**
 * 《Java多线程编程核心技术》第1章 Java多线程技能
 * 2.2.2 synchronized同步代码块的使用
 * Page： 74
 * 当两个并发线程访问同一个对象Object中的Synchronized(this)同步代码块时，
 * 一段时间内只能有一个线程被执行。另一个线程必须等待当前线程执行完这个代码块
 * 以后才能执行该代码块。
 * Author: wedo
 * Time: 2018-07-26 05:50:20
 */
public class ThreadSyncMethodUse {
    public static void main(String[] args) {
        ObjectService service = new ObjectService();
        ThreadA a = new ThreadA(service);
        a.setName("a");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("b");
        b.start();
    }
}
class ObjectService{
    protected void serviceMethod(){
        try{
            synchronized (this){
                System.out.println("begin time=" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("end   time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadA extends Thread{
    private ObjectService service;

    public ThreadA(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethod();
    }
}
class ThreadB extends Thread{
    private ObjectService service;

    public ThreadB(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethod();
    }
}
