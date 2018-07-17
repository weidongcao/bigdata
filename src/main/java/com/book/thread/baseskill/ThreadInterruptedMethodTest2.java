package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》第1章 Java多线程技能
 * 1.7.3 能停止的线程——异常法
 * Page：29
 * 在线程中用for语句来判断一下线程是否是停止状态，如果是停止状态，则后面的代码再不运行
 * 使用抛出异常的方式来解决线程停止后还在运行的问题
 * <p>
 * #Author wedo
 * #Time 2018-07-17 06:58:59
 */
public class ThreadInterruptedMethodTest2 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("已经是停止状态了！我要退出了！");
                    throw new InterruptedException();
                }
                System.out.println("i = " + (i + 1));
            }
            System.out.println("我在for循环之外<------------");
        } catch (InterruptedException e) {
            System.out.println("进入run方法的catch了！     ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ThreadInterruptedMethodTest2 thread = new ThreadInterruptedMethodTest2();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------- the end! ----------");
    }
}
