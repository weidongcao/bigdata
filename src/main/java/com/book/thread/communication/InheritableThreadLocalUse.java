package com.book.thread.communication;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 《Java多线程编程核心技术》第3章线程间通信
 * 3.4类InheritableThreadLocal的使用
 * 3.4.1 值继承
 * Page：197
 * 使用InheritableThreadLocal类可以让子线程从父线程中取得值
 *
 * Author:wedo
 * Time:2018-08-04 08:19:14
 */
public class InheritableThreadLocalUse {
    private static InheritableThreadLocal<String> itl = new InheritableThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return RandomStringUtils.randomAlphanumeric(10);
        }
    };

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("      在Main线程中取值 =" + itl.get());
            Thread.sleep(100);
        }
        Thread.sleep(3000);
        Thread ta = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("在ThreadA线程中取值 =" + itl.get());
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
