package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》 高洪岩 第1章 Java多线程技能
 *  1.4 isAlive()方法
 *  Page：19
 *  在使用isAlive()方法时，如果将线程对象以构造参数的方式传递给Thread对象进行Start()启动时，运行的结果和前面示例
 *  是有差异的，造成这样的差异的原因不是来自于Thread.currentThread()和this的差异
 *  @author wedo
 *  Time: 2018-07-15 08:14:43
 */
public class CountOperator extends Thread{
    public CountOperator(){
        System.out.println("CountOperator ---> begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println("CountOperator ---> end");
    }

    @Override
    public void run() {
        System.out.println("run ---> begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        //运行到这里的时候isAlive()的结果是false
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println("run ---> end");
    }

    public static void main(String[] args) {
        CountOperator c = new CountOperator();
        Thread t1 = new Thread(c);
        System.out.println("main begin t1 isAlive=" + t1.isAlive());
        t1.setName("A");
        t1.start();
        System.out.println("main end t1 isAlive = " + t1.isAlive());
    }
}
