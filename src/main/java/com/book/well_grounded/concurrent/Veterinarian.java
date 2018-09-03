package com.book.well_grounded.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 *
 * 《Java程序员的修炼之道》第4章 现代并发
 * 4.3.6 Queue
 * Page：93
 * 队列是一个非常美妙的抽象概念。不，之所以这么说并不是因为我们生活在伦敦这个
 * 世界排队之都。为把处理资源分发给工作单位（或者把工作单元分配给处理资源，
 * 这取决于你看待问题的方式），队列提供了一种简单又可选的方式。
 *
 * Java中有些多线程编程模式在很大程序上都依赖于Queue实现的线程安全性，所以很有
 * 必要充分认识它。Queue接口被放在了java.util包中，因为即使在单线程编程中它
 * 也是一个重要的模式，但我们的重点是多线程编程，并且假定你已经熟悉队列的基本用法了。
 *
 * 队列经常用来在线程之间传递工作单元，这个模式通常适合用Queue最简单的并发扩展
 * BlockingQueue来实现。接下来我们就会重点介绍它。
 *
 * BlockingQueue
 * BlockingQueue还有两个特性：
 * 在向队列中put()时，如果队列已满，它会让放入线程等等队列腾出空间。
 * 在从队列中take()时，如果队列为空，会导致取出线程阻塞。
 *
 * 这两个自反性非常有用，因为如果一个线程（或线程池）的能力超过了其他线程，比较快的
 * 线程就会被强制等等，因此可以对整个系统直到调节作用。
 *  BlockingQueue的两个实现
 *  Java提供了BlockingQueue接口的两个基本实现：
 *  LinkedBlockingQueue
 *  ArrayBlockingQueue
 *
 *  它们的自反性稍有不同；比如说，在已知队列的大小而能确定合适的边界时，用
 *  ArrayBlockingQueue非常高效，而LinkedBlockingQueue在某些情况下则会快一点儿。
 *
 *
 *  使用工作单元
 *  Queue接口全都是泛型的——它们是Queue<E>，BlockingQueue<E>，等等依此类推。尽管
 *  看起来奇怪，但有时候利用这一点把工作项封装在一个人工窗口类内却是明智之举。
 *  以后再在这种间接引用里增加元数据可能会非常困难。如果你发现在某些情况下需要更多的
 *  元数据，那么要把它们加入到间接引用中可能需要大量的重构工作，而加在WorkUnit类中
 *  就只是简单的修改。
 *
 * 一个BlockingUqueue的例子
 * 我们用一个简单的例子——等着看医生的宠物们——来看看如何使用BlockingQueue。这个例子
 * 中有一个等着让医生给做检查的宠物集合
 *
 * Created by CaoWeiDong on 2018-09-03.
 */
public class Veterinarian extends Thread {
    protected final BlockingQueue<Appointment<Pet>> appts;
    protected String text = "";
    protected final int restTime;
    private boolean shutdown = false;

    public Veterinarian(BlockingQueue<Appointment<Pet>> lbq, int pause) {
        appts = lbq;
        restTime = pause;
    }
    public synchronized void shutdown(){
        shutdown = true;
    }

    @Override
    public void run() {
        while (!shutdown) {
            seePatient();
            try{
                Thread.sleep(restTime);
            } catch (InterruptedException e) {
                shutdown = true;
            }
        }
    }

    /**
     * 在这个方法中线程会从队列中取出预约，并挨个检查对应的宠物，如果当前队列中没有预约
     * 等待则会阻塞
     */
    public void seePatient(){
        try {
            Appointment<Pet> ap = appts.take();
            Pet patient = ap.getPatient();
            patient.examine();
        } catch (InterruptedException e) {
            shutdown = true;
        }
    }
}
