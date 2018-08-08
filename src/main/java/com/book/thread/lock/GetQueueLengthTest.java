package com.book.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 * 4.1.10 方法 getHoldCount(), getQueueLength()和 getWaitQueueLength()的测试
 * Page：220
 * 方法int getQueueLength()的作用是返回下等待获取此锁定的线程估计数，比如有5个线程，1个线程首先执行await()
 * 方法，那么在调用getQueueLength()方法后返回值是4，说明有4个线程同时在等待lock的释放。
 * Author:wedo
 * Time:2018-08-09 07:45:59
 */
public class GetQueueLengthTest {
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
        final GetQueueLengthTest service = new GetQueueLengthTest();
        Thread[] arr = new Thread[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Thread(service::serviceMetnod1);
        }
        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
        Thread.sleep(2000);
        System.out.println("有线程数: " + service.lock.getQueueLength() + " 在等待获取锁");
    }
}
