package com.book.thread.communication;

/**
 * 《Java多线程核心技术》第3章线程间通信
 * 3.3.2 验证线程变更的隔离性
 * Page：193
 * Autho：wedo
 * Time：2018-08-04 07:41:43
 */
public class ThreadLocalInsulate {
    private static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread ta = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tl.set("ThreadA" + (i + 1));
                System.out.println("ThreadA get Value = " + tl.get());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread tb = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tl.set("ThreadB" + (i + 1));
                System.out.println("ThreadB getValue = " + tl.get());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ta.start();
        tb.start();
        for (int i = 0; i < 100; i++) {
            tl.set("Main" + (i + 1));
            System.out.println("Main get Value = " + tl.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
