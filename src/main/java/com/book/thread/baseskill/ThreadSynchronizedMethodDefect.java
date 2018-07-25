package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》第1章 Java多线程技能
 * 2.2.1 synchronized方法的弊端
 * Page： 72
 * 当两个并发线程访问同一个对象Object中的Synchronized(this)同步代码块时，
 * 一段时间内只能有一个线程被执行。另一个线程必须等待当前线程执行完这个代码块
 * 以后才能执行该代码块。
 * Created by Cao Wei Dong on 2018-07-26.
 */
public class ThreadSynchronizedMethodDefect {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        MyThread1 t1 = new MyThread1(task);
        t1.start();
        MyThread2 t2 = new MyThread2(task);
        t2.start();
        Thread.sleep(10000);
        long beginTime = Utils.beginTime1 > Utils.beginTime2 ? Utils.beginTime1 : Utils.beginTime2;
        long endTime = Utils.endTime1 > Utils.endTime2 ? Utils.endTime1 : Utils.endTime2;
        System.out.println("耗时： " + ((endTime - beginTime) / 1000));
    }
}
class Task{
    private String getData1;
    private String getData2;
    public synchronized void doLongTimeTask(){
        try{
            System.out.println("Begin Task");
            Thread.sleep(3000 );
            getData1 = "长时间处理任务后从远程返回的值 1 ThreadName= " + Thread.currentThread().getName();
            getData2 = "长时间处理任务后从远程返回的值 2 ThreadName= " + Thread.currentThread().getName();
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("End Task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Utils{
    protected static long beginTime1;
    protected static long endTime1;
    protected static long beginTime2;
    public static long endTime2;
}
class MyThread1 extends Thread{
    private Task task;

    public MyThread1(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        Utils.beginTime1 = System.currentTimeMillis();
        task.doLongTimeTask();
        Utils.endTime1 = System.currentTimeMillis();
    }
}

class MyThread2 extends Thread {
    private Task task;

    public MyThread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        Utils.beginTime2 = System.currentTimeMillis();
        task.doLongTimeTask();
        Utils.endTime2 = System.currentTimeMillis();
    }
}
