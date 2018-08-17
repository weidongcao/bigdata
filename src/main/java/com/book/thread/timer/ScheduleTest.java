package com.book.thread.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 《Java多线程编程核心技术》第5章 定时器Timer
 * 5.1.2 方法schedule(TimerTask task, Date firstTime, long period)的测试
 * Page：247
 * 该方法的作用是在指定的日期安指定的间隔周期，无限循环地执行某一任务。
 * Author：wedo
 * Time：2018-08-18 06:20:30
 */
public class ScheduleTest {
    public static void main(String[] args) {
        System.out.println("当前时间：" + new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        Date date = calendar.getTime();
        System.out.println("计划时间：" + date);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("任务执行了，时间为: " + new Date());
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, date, 4000);
    }
}
