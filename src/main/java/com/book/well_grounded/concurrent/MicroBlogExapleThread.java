package com.book.well_grounded.concurrent;

import com.book.well_grounded.Author;
import com.book.well_grounded.Update;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by CaoWeiDong on 2018-09-03.
 */
public abstract class MicroBlogExapleThread extends Thread {
    protected final BlockingQueue<Update> updates;
    protected String text = "";
    protected final int pauseTime;
    private boolean shutdown = false;

    public MicroBlogExapleThread(BlockingQueue<Update> lbq_, int pause_) {
        updates = lbq_;
        pauseTime = pause_;
    }

    public synchronized void shutdown() {
        shutdown = true;
    }

    @Override
    public void run() {
        while (!shutdown) {
            doAction();
            try {
                Thread.sleep(pauseTime);
            } catch (InterruptedException e) {
                shutdown = true;
            }
        }
    }

    public abstract void doAction();

    public static void main(String[] args) {

        final Update.Builder ub = new Update.Builder();
        final BlockingQueue<Update> lbq = new LinkedBlockingQueue<>(100);
        MicroBlogExapleThread t1 = new MicroBlogExapleThread(lbq, 10) {
            @Override
            public void doAction() {
                text = text + "X";
                Update u = ub.author(new Author("Tallulah")).updateText(text).build();
                boolean handed = false;
                try {
                    handed = updates.offer(u, 100, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                }
                if (!handed)
                    System.out.println("Unable to hand off Update to Queue due to timeout");
            }
        };

        MicroBlogExapleThread t2 = new MicroBlogExapleThread(lbq, 1000) {
            @Override
            public void doAction() {
                Update u = null;
                try {
                    u = updates.take();
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        t1.start();
        t2.start();
    }
}
