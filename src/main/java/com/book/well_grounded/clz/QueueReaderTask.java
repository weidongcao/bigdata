package com.book.well_grounded.clz;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 《Java程序员的修炼之道》第5章 类文件与字节码
 * 5.2.4 示例：反向、代理与方法句柄
 * Page：114
 * 用于演示方法句柄与反向、代理的简洁
 * Created by Cao Wei Dong on 2018-09-05 06:42.
 */
public abstract class QueueReaderTask implements Runnable {
    private volatile boolean shutdown = false;
    protected BlockingQueue<WorkUnit<String>> q;
    private final int pollTime;

    public QueueReaderTask(int i) {
        pollTime = i;
    }
    public void shutdown() {
        shutdown = true;
    }
    @Override
    public void run() {
        while (!shutdown) {
            try{
                WorkUnit<String> wu = q.poll(pollTime, TimeUnit.MILLISECONDS);
                if (wu != null) {
                    doAction(wu.getWork());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void doAction(String msg);

    public void setQueue(BlockingQueue<WorkUnit<String>> lbq) {
        q = lbq;
    }
}
