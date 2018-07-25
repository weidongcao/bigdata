package com.book.thread.visit;

/**
 * 《Java多线程编程核心技术》第1章 Java多线程技能
 * 2.2.3 用同步代码块解决同步方法的弊端
 * Page： 76
 * 当两个并发线程访问同一个对象Object中的Synchronized(this)同步代码块时，
 * 一段时间内只能有一个线程被执行。另一个线程必须等待当前线程执行完这个代码块
 * 以后才能执行该代码块。
 * Author: wedo
 * Time:2018-07-26 06:17:46
 */
public class ThreadSyncMethodSolve {
    protected static long beginTime1;
    protected static long endTime1;
    protected static long beginTime2;
    public static long endTime2;
    private String getData1;
    private String getData2;
    public  void doLongTimeTask(){
        try{
            System.out.println("Begin Task");
            Thread.sleep(3000 );
            synchronized(this){
                getData1 = "长时间处理任务后从远程返回的值 1 ThreadName= " + Thread.currentThread().getName();
                getData2 = "长时间处理任务后从远程返回的值 2 ThreadName= " + Thread.currentThread().getName();
            }
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("End Task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadSyncMethodSolve task = new ThreadSyncMethodSolve();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                super.run();
                beginTime1 = System.currentTimeMillis();
                task.doLongTimeTask();
                endTime1 = System.currentTimeMillis();
            }
        };
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                super.run();
                beginTime2 = System.currentTimeMillis();
                task.doLongTimeTask();
                endTime2 = System.currentTimeMillis();
            }
        };
        t2.start();
        Thread.sleep(10000);
        long beginTime = beginTime1 > beginTime2 ? beginTime1 : beginTime2;
        long endTime = endTime1 > endTime2 ? endTime1 : endTime2;
        System.out.println("耗时： " + ((endTime - beginTime) / 1000));
    }
}

