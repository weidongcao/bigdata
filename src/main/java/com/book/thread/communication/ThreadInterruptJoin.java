package com.book.thread.communication;

/**
 * 《Java多线程编程核心技术》第3章 线程间通信
 * 3.2.3 方法join与异常
 * Page：182
 * 在join过程中，如果当前线程对象被中断，则当前线程出现异常。
 *
 * Author：wedo
 * Time:2018-08-01 06:37:08
 */
public class ThreadInterruptJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread tb = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread ta = new Thread(() -> {
                        for (int i = 0; i < Integer.MAX_VALUE; i++) {
                            String str = "";
                            Math.random();
                        }
                    });
                    ta.start();
                    ta.join();
                    System.out.println("线程B在run end处打印了");
                } catch (InterruptedException e) {
                    System.out.println("线程B在catch处打印了");
                    e.printStackTrace();
                }
            }
        };
        tb.start();
        Thread.sleep(200);
        Thread tc = new Thread(tb::interrupt);
        tc.start();
    }
}
