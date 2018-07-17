package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》第1章 Java多线程技能
 * 1.7.3 能停止的线程——异常法
 * Page：27
 * 在线程中用for语句来判断一下线程是否是停止状态，如果是停止状态，则后面的代码再不运行
 * 经测试我们会发现如果for语句下面还有语句，不是会继续运行的。
 * #Author wedo
 * #Time 2018-07-17 06:50:19
 */
public class ThreadInterruptedMethodTest1 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.isInterrupted()) {
                System.out.println("已经停止状态了！要我退出了！");
                break;
            }
            System.out.println("i = " + (i + 1));
        }
        System.out.println("我在for循环之外<------------");

    }

    public static void main(String[] args) {
        try {
            ThreadInterruptedMethodTest1 thread = new ThreadInterruptedMethodTest1();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------The End--------");
    }
}
