package com.book.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 * 4.1.10 方法 getHoldCount(), getQueueLength()和 getWaitQueueLength()的测试
 * Page：220
 * 方法int getWaitQueueLength(Condition condition)的作用是返回等待与此锁定相关的给定
 * 条件Condition的线程估计数，有5个线程，每个线程都执行了同一个Condition对象的await()
 * 方法，则调用 getWaitQueueLength(Condition condition)方法时返回的int 值是5
 */
public class GetWaitQueueLengthTest {
    private ReentrantLock lock = new ReentrantLock();
    private void serviceMetnod1(){
        try{
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "进入方法！");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final GetWaitQueueLengthTest service = new GetWaitQueueLengthTest();
        Thread[] arr = new Thread[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Thread() {
                @Override
                public void run() {
                    service.serviceMetnod1();
                }
            };
        }
        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
        Thread.sleep(2000);
        System.out.println("有线程数: " + service.lock.getQueueLength() + " 在等待获取锁");
    }
}
