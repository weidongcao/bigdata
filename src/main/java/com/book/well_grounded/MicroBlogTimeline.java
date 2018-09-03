package com.book.well_grounded;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 《Java程序员的修炼之道》第4章 现代并发
 * 4.3.5 CopyOnWriteArrayList
 * Page:88
 * 从名字就能看出来，CopyOnWriteArrayList是标准ArrayList的替代品。CopyOnWriteArrayList
 * 通过增加写时复制（copy-on-write）主义来实现线程安全性，也就是说修改列表的任何操作都会创建
 * 一个列表底层数组的新复本。这就意味着所有成型的迭代器都不用担心它们会碰到意料之外的修改。
 *
 * 当快速，一致的数据快照（不同的读取器读到的数据偶尔可能会不一样）比完美的同步以及性能上的突破
 * 更重要时，这种共享数据的方法非常理想，并经常出现在非关键任务中。
 *
 * 我们来看一个写时复制的安全。假设有个微博的时间线更新，这是一个典型的非关键任务的例子。每个读
 * 取器的性能、自身一致性的快照要比全局的一致性更受欢迎。
 *
 * Created by Cao Wei Dong on 2018-09-03 08:31.
 */
public class MicroBlogTimeline {
    private final CopyOnWriteArrayList<Update> updates;
    private final ReentrantLock lock;
    private final String name;
    private Iterator<Update> it;

    public MicroBlogTimeline(CopyOnWriteArrayList<Update> list, ReentrantLock lock_, String name_) {
        updates = list;
        lock = lock_;
        name = name_;
    }
    public void addUpdate(Update update_) {
        updates.add(update_);
    }

    public void prep() {
        it = updates.iterator();
    }
    public void printTimeline() {
        lock.lock();
        try{
            if (it != null) {
                System.out.println(name + ": ");
                while (it.hasNext()) {
                    Update s = it.next();
                    System.out.println(s + ", ");
                }
                System.out.println();
            }
        }finally {
            lock.unlock();
        }
    }
}
