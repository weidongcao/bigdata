package com.book.thread.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * 《Java多线程编程核心技术》第3章 线程间通信
 * 3.1.3等待通知机制的实现
 * Page： 139
 * 方法 wait()的作用是使当前执行代码的线程进行等待，wait()方法是Object类的方法，
 * 该方法用来将当前线程置入"预执行队列"中，并且在wait()所在的代码行处停止执行，直到
 * 接到通知或被中断为止。在调用wait()之前，线程必须获得该对象的对象级别锁，即只能在
 * 同步方法或同步块中调用 wait()方法。在执行wait()方法后，当前线程释放锁。在从wait()
 * 返回前，线程与其他线程竞争重新获得锁。如果调用 wait()时没有持有适当的锁，则抛出
 * IllegalMonitorStateException，它是RuntimeException的一个子类，因此，不需要
 * try-catch语句进行捕捉异常。
 * <p>
 * 方法notify()也要在同步方法或同步块中调用，即在调用前，线程也必须获得该对象的对象
 * 级别锁。如果调用notify()时没有持有适当的锁，也会抛出IllegalMoniotrStateException
 * 该方法用来通知那些可能等待该对象的对象锁的其他线程，如果有多个线程等待，则由线程规划器
 * 随机换行出其中一个呈wait状态的线程，对其发出通知notify，并使它等待获取该对象的对象锁。
 * 需要说明的是，在执行notify()方法后，当前线程不会马上释放该对象锁，呈wait状态的线程也
 * 并不能马上获取该对象锁，要等到执行notify()方法的线程将程序执行完，也就是退出synchronized
 * 代码块后,当前线程都会释放锁，而呈wait状态所在的线程才可以获取该对象锁。当第一个获得了
 * 该对象锁的wait线程运行完毕以后，它会释放掉该对象锁，此时如果该对象没有再次使用notify语句，
 * 则即使该对象已经空闲，其他 wait状态等待的线程由于没有得到该对象的通知，也还会继续阻塞在
 * wait状态，直到这个对象发出一个notify或notifyAll。
 * <p>
 * 用一顿饭话来总结一下wait和nofity：wait使线程停止运行，而notify使停止的线程继续等待。
 * Author: wedo
 * Time:2018-07-26 06:17:46
 */
public class ThreadNotifyAdd {
    private static List<String> list = new ArrayList<>();

    private static void add() {
        list.add("wedo...");
    }

    private static int size() {
        return list.size();
    }

    /*
     * 关键字synchronized可以将任何一个Object对象作为同步对象来看待，而Java为每个Object
     * 都实现了wait()和notify()方法，它们必须用在被synchronized同步的Object的临界区内。
     * 通过调用 wait()方法可以使处于临界区内的线程进行等待状态，同时释放被同步对象的锁。
     * 而nofity()操作可以唤醒一个因调用 了wait操作而牌阻塞状态中的线程，使其进入就绪状态。
     * 被重新唤醒的线程会试图重新获得临界区的控制权，也就是锁，并继续执行临界区内wait之后
     * 的代码。如果发出notify操作时没有牌阻塞状态中的线程，那么该命令会被忽略。
     *
     * wait()方法可以使调用该方法的线程释放共享资源的锁，然后从运行状态退出，进入等待队列，
     * 直到被再次唤醒。
     *
     * notify()方法可以随机唤醒等待队列中等待同一共享资源的"一个"线程，并使该线程退出等待队列，
     * 进入可运行状态，也就是notify()方法仅通知"一个"线程。
     *
     * notifyAll()方法可以使所有正在等待队列中等待同一共享资源的"全部"线程从等待状态退出，
     * 进入可运行状态。此时，优先级最高的那个线程最先执行，但也有可能是随机执行的，因为这要取决于
     * JVM虚拟机的实现。
     *
     * 线程运行的所有状态:
     * 1. 新创建一个新的线程对象后，再调用它的start()方法，系统会为此线程分配CPU资源，使其处于
     * Running状态
     *
     * 2. Runnable和Running状态可相互切换，因为有可能线程运行一段时间后，有其他高优先级的线程
     * 抢占了CPU资源，这里此线程就从Running状态变成Runnable状态。
     * 线程进入Runnable状态大体分为如下5种情况：
     * 一、调用 sleep()方法后经过的时间超过了指定的休眠时间。
     * 二、线程调用的阻塞IO已经返回，阻塞方法执行完毕。
     * 三、线程成功地获得了试图同步的监视器
     * 线程正在等待某个通知，其他线程发出了通知
     * 牌扶起状态的线程调用了resume恢复方法。
     *
     * 3. Blocked是阻塞的意思，例如遇到了一个IO操作，此时CPU处于空闲状态，可能会转而把CPU时间片
     * 分配给其他线程，这里也可以称为"暂停"状态。Blocked状态结束后，进入Runnable状态，等待系统
     * 重新分配资源。
     *
     * 出现阻塞的情况大体分为5种：
     * 一、线程调用sleep方法，主动放弃占用的处理器资源。
     * 二、线程调用了阻塞式IO方法，在该方法返回前，该线程被阻塞
     * 三、线程试图获得一个同步监视器，但该同步监视器正被其他线程所持有。
     * 四、线程等待某个通知
     * 五、程序调用 了suspend方法将该线程挂起。
     *
     * 4. run()方法运行结束后进入销毁阶段，整个线程执行完毕。
     * 每个锁对象都有两个队列，一个是就绪队列，一个是阻塞队列。就绪队列存储了将要获得锁的线程，
     * 阻塞队列存储了被的线程。一个线程被唤醒后，都会进入就绪队列，等待CPU的调度；反之，一个线程被wait
     * 后，就会进入阻塞队列，等待下一餐被唤醒。
     * @param args
     *
     */
    public static void main(String[] args) {
        Object lock = new Object();
        Thread ta = new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (lock) {
                    if (size() != 5) {
                        System.out.println("Wait begin --> " + System.currentTimeMillis());
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Wait   end --> " + System.currentTimeMillis());

                    }
                }
            }
        };
        ta.start();

        Thread tb = new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        add();
                        if (size() == 5) {
                            lock.notify();
                            System.out.println("已经发出唤醒通知...");
                        }
                        System.out.println("添加了 --> 第 " + (i + 1) + " 个元素！");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        tb.start();
    }
}
