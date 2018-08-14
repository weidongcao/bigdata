package com.book.thread.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 《Java多线程编程核心技术》第5章 定时器Timer
 * 5.1 定时器Timer的使用
 * Page：243
 * 定时/计划功能在移动开发领域使用较多，比如Android技术，定时计划任务功能在Java中主要使用的就是Timer对象，
 * 它在内部不是使用多线程的方式进行处理，所以它和线程技术不是有非常大的关联。本章节着重掌握如下技术点：
 * 1.如何实现指定时间执行任务
 * 2.如何实现按指定周期执行任务
 *
 * 在JDK库中，Timer类主要负责计划任务的功能，也就是在指定的时间开始执行某一个任务，Timer类的方法列表如图5-1所示：
 * Timer类的主要作用就是设置计划任务，但封闭任务的类却是TimerTask类，类结构如图5-2所示：
 * 执行计划任务的代码要放入TimerTask的子类中，因为TimerTask是一个抽象类。
 * 方法schedule(TimerTask task, Date time)该方法的作用是在指定的日期执行一次某一任务。
 *
 */
public class TimerScheduleTest {
    public static void main(String[] args) {
        System.out.println("当前时间为：" + new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -10);
        Date date = calendar.getTime();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("任务执行了，时间为：" + new Date());
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, date);

    }
}
