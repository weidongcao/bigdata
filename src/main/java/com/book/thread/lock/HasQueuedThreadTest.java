package com.book.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 *  4.1.11 方法 hasQueuedThread(),hasQueuedThreads()和hasWaiters()的测试
 *  Page：222
 *  方法 hasQueuedThread(Thread thread)的作用是查询指定的纯种是否正在等等获取此锁定
 *  方法 hasQueuedThreads()的作用是是否有线程正在等等获取此锁定
 *  Author: wedo
 *  Time:2018-08-11 06:54:42
 */
public class HasQueuedThreadTest {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private void waitMethod(){
        try{
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final HasQueuedThreadTest service = new HasQueuedThreadTest();
        Runnable runnable = () -> service.waitMethod();
        Thread ta = new Thread(runnable);
        ta.start();
        Thread.sleep(500);
        Thread tb = new Thread(runnable);
        tb.start();
        Thread.sleep(500);
        System.out.println("service.lock.hasQueuedThread(ta) = " + service.lock.hasQueuedThread(ta));
        System.out.println("service.lock.hasQueuedThread(tb) = " + service.lock.hasQueuedThread(tb));
        System.out.println("service.lock.hasQueuedThread() = " + service.lock.hasQueuedThreads());
    }
}
