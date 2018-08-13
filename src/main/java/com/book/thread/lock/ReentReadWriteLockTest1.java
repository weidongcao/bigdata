package com.book.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 * 4.2 使用ReentrantReadWriteLock类
 * Page：236
 * 类ReentrantLock具有完全互斥排他的效果，即同一时间只有一个线程在执行ReentrantLock.lock()
 * 方法后面的任务。这样做虽然保证了实例变量的线程安全性，但效率却是非常低下的。所以在JDK中提供了一种读写锁
 * ReentrantReadWriteLock类，使用它可以加快运行效率，在某些不需要操作实例变量的方法中，完全可以使用读写锁
 * ReentrantReadWriteLock来提升该方法的运行速度。
 * 读写锁表示有两个锁，一个是读操作相关的锁，也称为共享锁；另一个是写操作相关的锁，也叫排他锁。也就是多个读锁
 * 之间不互斥，读锁怀写锁互斥，写锁与写锁互斥。在没有线程Trhead进行写入操作时，进行读取操作的多个Trhead都可
 * 以获取读锁，而进行写入操作的Thread只有在获取写锁后才能进行写入操作。即多个Thread可以同时进行读取操作。
 * 但是同一时刻只允许一个Thread进行写入操作。
 * Author：wedo
 * Time:2018-08-14 07:32:51
 */
public class ReentReadWriteLockTest1 {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private void read(){
        try{
            lock.readLock().lock();
            System.out.println("获得读锁 " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.readLock().unlock();
        }
    }
    private void write(){
        try{
            lock.writeLock().lock();
            System.out.println("获得写锁 " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
    //读读共享测试
    private static void readTest(ReentReadWriteLockTest1 service){
        Thread ta = new Thread() {
            @Override
            public void run() {
                service.read();
            }
        };
        ta.setName("ThreadA");
        Thread tb = new Thread() {
            @Override
            public void run() {
                service.read();
            }
        };
        tb.setName("ThreadB");
        ta.start();
        tb.start();
    }
    //写写互斥测试
    private static void writedTest(ReentReadWriteLockTest1 service){
        Thread ta = new Thread() {
            @Override
            public void run() {
                service.write();
            }
        };
        ta.setName("ThreadA");
        Thread tb = new Thread() {
            @Override
            public void run() {
                service.write();
            }
        };
        tb.setName("ThreadB");
        ta.start();
        tb.start();
    }
    //写写互斥测试
    private static void readwritedTest(ReentReadWriteLockTest1 service){
        Thread ta = new Thread() {
            @Override
            public void run() {
                service.read();
            }
        };
        ta.setName("ThreadA");
        Thread tb = new Thread() {
            @Override
            public void run() {
                service.write();
            }
        };
        tb.setName("ThreadB");
        tb.start();
        ta.start();
    }
    public static void main(String[] args) {
        ReentReadWriteLockTest1 service = new ReentReadWriteLockTest1();
//        readTest(service);
//        writedTest(service);
        readwritedTest(service);
    }
}
