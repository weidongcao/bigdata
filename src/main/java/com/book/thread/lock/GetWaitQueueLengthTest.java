package com.book.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 * 4.1.10 方法 getHoldCount(), getQueueLength()和 getWaitQueueLength()的测试
 * Page：220
 * 方法int getWaitQueueLength(Condition condition)的作用是返回等待与此锁定相关的给定
 * 条件Condition的线程估计数，有5个线程，每个线程都执行了同一个Condition对象的await()
 * 方法，则调用 getWaitQueueLength(Condition condition)方法时返回的int 值是5
 * Author:wedo
 * Time:2018-08-09 07:45:59
 */
public class GetWaitQueueLengthTest {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private void serviceMethod(){
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    private void notityMethod(){
        try{
            lock.lock();
            System.out.println("有 " +lock.getWaitQueueLength(condition) +  " 个线程正在等待condition");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final GetWaitQueueLengthTest service = new GetWaitQueueLengthTest();
        Thread[] arr = IntStream.range(0, 10).mapToObj(i -> new Thread(service::serviceMethod)).toArray(Thread[]::new);
        IntStream.range(0, 10).forEach(i -> arr[i].start());
        Thread.sleep(2000);
        service.notityMethod();
    }
}
