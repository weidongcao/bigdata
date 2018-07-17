package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》第1章 Java多线程技能
 * 1.7.4 在沉睡中停止
 * Page：31
 * 先停止再沉睡
 * <p>
 * #Author wedo
 * #Time 2018-07-17 06:58:59
 */
public class ThreadInterruptedMethodTest3 extends Thread{
    @Override
    public void run() {
        super.run();
        try{
            for (int i = 0; i < 230000; i++) {
                System.out.println("i = " + (i + 1));
            }
            System.out.println("run begin");
            Thread.sleep(20000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("先停止，再遇到了sleep！进入catch!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadInterruptedMethodTest3 thread = new ThreadInterruptedMethodTest3();
        thread.start();
        /*
         * 方法stop已经作废，因为如果强制让线程停止则有可能使一些清理性的工作得不到完成。
         * 另外一个情况就是对锁定的对象进行了"解锁"，导致数据得不到同步的处理，出现数据不一致
         * 的问题
         */
//        thread.stop();
        thread.interrupt();
        System.out.println("----- the end! -----");
    }
}
