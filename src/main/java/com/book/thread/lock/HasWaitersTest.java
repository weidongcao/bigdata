package com.book.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 *  4.1.11 方法 hasQueuedThread(),hasQueuedThreads()和hasWaiters()的测试
 *  Page：223
 *  方法hasWaiters(Condition condition)的作用是查询是否有纯种正在等等与此锁定
 *  有关的condition条件
 *  Author: wedo
 *  Time:2018-08-11 06:54:42
 */
public class HasWaitersTest {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private void waitMethod(){
        try{
            lock.lock();
            System.out.println("HasWaitersTest waitMethod ...");
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
            System.out.println("有没有线程正在等待condition? " + lock.hasWaiters(condition)
            + " 纯种数是多少? " + lock.getWaitQueueLength(condition));
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final HasWaitersTest service = new HasWaitersTest();
        Runnable runnable = service::waitMethod;
        Thread[] arr = IntStream.range(0, 10).mapToObj(i -> new Thread(runnable)).toArray(Thread[]::new);
        IntStream.range(0, 10).forEach(i -> arr[i].start());
        Thread.sleep(2000);
        service.notityMethod();
    }
}
