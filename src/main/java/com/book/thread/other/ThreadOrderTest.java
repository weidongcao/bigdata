package com.book.thread.other;

/**
 * 《Java多线程编程核心技术》第7章拾贵增补
 * 7.3 使线程具有有序性
 * Page：292
 * 正常情况下，线程在运行时多个线程之间执行任务的时机是无序的。
 * 可以通过发行代码的方式使它们运行具有有序性。
 *
 */
public class ThreadOrderTest {
    public static void main(String[] args) {
        Object lock = new Object();
        ThreadTest ta = new ThreadTest(lock, "A", 1);
        ThreadTest tb = new ThreadTest(lock, "B", 2);
        ThreadTest tc = new ThreadTest(lock, "C", 0);
        ta.start();
        tb.start();
        tc.start();
    }
}
class ThreadTest extends Thread{
    private Object lock;
    private String showChar;
    private int showNumPosition;
    private int printCount = 0;
    volatile private static int addNumber = 1;

    public ThreadTest(Object lock, String showChar, int showNumPosition) {
        this.lock = lock;
        this.showChar = showChar;
        this.showNumPosition = showNumPosition;
    }

    @Override
    public void run() {
        try{
            synchronized (lock) {
                while (true) {
                    if (addNumber % 3 == showNumPosition) {
                        System.out.println("ThreadName = " + Thread.currentThread().getName() + " runCount= " + addNumber + " " + showChar);
                        lock.notifyAll();
                        addNumber++;
                        printCount++;
                        if (printCount == 3) {
                            break;
                        }
                    } else {
                        lock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
