package com.book.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 * 4.1 使用ReentrantLock类
 * 4.1.1使用ReentrantLock实现同步
 * Page：201
 * 在Javaa多线程中，可以使用synchronized关键字来实现线程之间同步互斥，
 * 但是在JDK1.5中新增加了ReentrantLock类也能达到同样的效果，并且在扩展
 * 功能上也更加强大，比如具有嗅探锁定、多路分支通知等功能，而且在使用上也
 * 比synchronized更加灵活。
 * Author：wedo
 * Time:2018-08-05 09:32:27
 */
public class ReentrantLockTest {
    private Lock lock = new ReentrantLock();
    public void methodTest(){
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName = " + Thread.currentThread() + (" " + (i + 1)));
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockTest tr = new ReentrantLockTest();
        Thread[] arr = new Thread[5];
        for (int i = 0; i < arr.length; i++) {
            Thread thread = new Thread(tr::methodTest);
            arr[i] = thread;
        }
        IntStream.range(0, arr.length).forEach(i -> arr[i].start());
    }
}
