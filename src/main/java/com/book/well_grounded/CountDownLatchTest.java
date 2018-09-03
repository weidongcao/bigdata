package com.book.well_grounded;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 《Java程序员的修炼之道》第4章 现代并发
 * 4.3.3 CountDownLatch
 * CountDownLatch是一种简单的同步模式，这种模式允许线程在通过同步屏障之前做些
 * 少量的准备工作。
 * 为了达到这种效果，在构建新的CountDownLatch实例时要给它提供一个int值（计数器）。
 * 此外，还有两个用来控制锁存器的方法：countDown()和await()。前者对计数器减1，
 * 后者让调用线程在计数器到0之前一直等待。如果计数器已经为0或更小，则它什么也不做。
 * 这个简单的机制使得这种所需准备最少的模式非常容易部署。
 * 在下面的代码中，同一进程内的一线更新处理线程至少必须有一半线程正确初始化（假定
 * 更新处理线程的初始化要占用不一定时间）之后，才能开始接受系统发送给它们中的任何
 * 一个线程的更新。
 *
 * Created by Cao Wei Dong on 2018-09-03 07:09.
 */
public class CountDownLatchTest extends Thread {
    private final String ident;
    private final CountDownLatch latch;

    public CountDownLatchTest(String ident_, CountDownLatch cdl_) {
        ident = ident_;
        latch = cdl_;
    }

    public String getIdentifier() {
        return ident;
    }

    public void initialize() {
        latch.countDown();
    }

    public void run() {
        initialize();
    }

    public static void main(String[] args) {

        long MAX_THREADS = 1000;
        final int quorm = 1 + (int) (MAX_THREADS / 2);
        final CountDownLatch cdl = new CountDownLatch(quorm);
        final Set<CountDownLatchTest> nodes = new HashSet<>();
        try {
            for (int i = 0; i < MAX_THREADS; i++) {
                CountDownLatchTest local = new CountDownLatchTest("localhost:" + (9000 + i), cdl);
                nodes.add(local);
                local.start();
            }
            cdl.await();
        } catch (InterruptedException e) {

        } finally {

        }
    }
}
