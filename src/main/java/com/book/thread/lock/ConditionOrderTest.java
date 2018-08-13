package com.book.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 《Java多线程编程核心技术》第4章 Lock的使用
 * 4.1.16 使用Condition实现顺序执行
 * Page：236
 * 使用Condition对象可以对线程执行的业务进行排序规划
 * Author：wedo
 * Time：2018-08-14 07:05:03
 */
public class ConditionOrderTest {
    volatile private static int nextPrintWho = 1;
    private static ReentrantLock lock = new ReentrantLock();
    final private static Condition conA = lock.newCondition();
    final private static Condition conB = lock.newCondition();
    final private static Condition conC = lock.newCondition();

    public static void main(String[] args) {
        Thread ta = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 1) {
                    conA.await();
                }
                IntStream.range(0, 3).mapToObj(i -> "ThreadA " + (i + 1)).forEach(System.out::println);
                nextPrintWho = 2;
                conB.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread tb = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 2) {
                    conB.await();
                }
                IntStream.range(0, 3).mapToObj(i -> "ThreadB " + (i + 1)).forEach(System.out::println);
                nextPrintWho = 3;
                conC.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread tc = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 3) {
                    conC.await();
                }
                IntStream.range(0, 3).mapToObj(i -> "ThreadC " + (i + 1)).forEach(System.out::println);
                nextPrintWho = 3;
                conA.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        Thread[] aarr = new Thread[5];
        Thread[] barr = new Thread[5];
        Thread[] carr = new Thread[5];
        IntStream.range(0, 5).forEach(i -> {
            aarr[i] = new Thread(ta);
            barr[i] = new Thread(tb);
            carr[i] = new Thread(tc);
            aarr[i].start();
            barr[i].start();
            carr[i].start();
        });
    }
}
