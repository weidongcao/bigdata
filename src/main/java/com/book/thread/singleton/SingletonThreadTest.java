package com.book.thread.singleton;

/**
 * 《Java多线程编程核心技术》第6章单例模式与多线程
 * 6.2 延迟加载/懒汉模式
 * Page:271
 * 使用双重检查锁机制来实现多线程环境中的延迟加载单例设计模式
 * Author：wedo
 * Timer：2018-08-20 06:08:07
 */
public class SingletonThreadTest {
    private volatile static SingletonThreadTest obj = new SingletonThreadTest();

    private SingletonThreadTest() {
    }

    public static SingletonThreadTest getInstance(){
        try {

            if (obj == null) {
                Thread.sleep(3000);
                if (obj == null) {
                    synchronized (SingletonThreadTest.class) {
                        obj = new SingletonThreadTest();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(SingletonThreadTest.getInstance().hashCode());
        Thread ta = new Thread(runnable);
        Thread tb = new Thread(runnable);
        Thread tc = new Thread(runnable);
        ta.start();
        tb.start();
        tc.start();
    }
}
