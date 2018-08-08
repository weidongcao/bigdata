package com.book.thread.lock;

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
}
