package com.book.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 * 4.1.6 使用多个Condition实现通知部分线程：正确用法
 * Page：211
 *
 * Author：wedo
 * Time：2018-08-06 07:21:19
 */
public class ReentrantLockConditionMany {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private void awaitA(){
        try{
            lock.lock();
            System.out.println("Begin awaitA 时间为：" + System.currentTimeMillis() + " ThreadName= " + Thread.currentThread().getName());
            conditionA.await();
            System.out.println("  End awaitA 时间为：" + System.currentTimeMillis() + " ThreadName= " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private void awaitB(){
        try{
            lock.lock();
            System.out.println("begin awaitB时间为 " + System.currentTimeMillis() + " ThreadName= " + Thread.currentThread().getName());
            conditionB.await();
            System.out.println("  End awaitB时间为 " + System.currentTimeMillis() + " ThreadName= " + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    private void signalAll_A(){
        try{
            lock.lock();
            System.out.println("  signalAll_A时间为" + System.currentTimeMillis() + " ThreadName= " + Thread.currentThread().getName());
            conditionA.signalAll();
        }finally {
            lock.unlock();
        }
    }
    private void signalAll_B(){
        try{
            lock.lock();
            System.out.println(" signalAll_B时间为" + System.currentTimeMillis() + " ThreadName= " + Thread.currentThread().getName());
            conditionB.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockConditionMany service = new ReentrantLockConditionMany();
        Thread ta = new Thread(service::awaitA);
        ta.setName("A");
        ta.start();
        Thread tb = new Thread(service::awaitB);
        tb.setName("B");
        tb.start();
        Thread.sleep(3000);
        service.signalAll_A();
    }
}
