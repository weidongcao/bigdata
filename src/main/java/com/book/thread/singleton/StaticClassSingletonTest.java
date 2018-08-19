package com.book.thread.singleton;

import java.io.Serializable;

/**
 * 《Java多线程编程核心技术》第6章 单例模式与多线程
 * 6.3 使用静态内置类实现单例模式
 * Page：273
 * 使用双重检查锁可以解决多线程单例模式的非线程安全问题，当然，使用其他的办法也能达到同样的效果
 * 比如说使用静态内部类
 * Author：wedo
 * Time: 2018-08-20 06:17:18
 */
public class StaticClassSingletonTest implements Serializable{
    private static class MyObj{
        private static StaticClassSingletonTest obj = new StaticClassSingletonTest();
    }
    private StaticClassSingletonTest(){}
    public static StaticClassSingletonTest getInstance(){
        return MyObj.obj;
    }

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(StaticClassSingletonTest.getInstance().hashCode());
        Thread ta = new Thread(runnable);
        Thread tb = new Thread(runnable);
        Thread tc = new Thread(runnable);
        ta.start();
        tb.start();
        tc.start();
    }
}
