package com.book.thread.communication;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 《Java多线程核心技术》第3章线程间通信
 * 3.3.4 验证线程变量的隔离性
 * Page：196
 * Autho：wedo
 * Time:2018-08-04 08:06:47
 */
public class ThreadLocalInsulateNull {
    private static ThreadLocal<String> tl = ThreadLocal.withInitial(() -> RandomStringUtils.randomAlphanumeric(10));

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("      在Main线程中取值=" + tl.get());
            Thread.sleep(100);
        }
        Thread.sleep(2000);
        Thread ta = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("在ThreadA线程中取值=" + tl.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ta.start();
    }
}
