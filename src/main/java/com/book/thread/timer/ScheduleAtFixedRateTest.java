package com.book.thread.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 《Java多线程编程核心技术》第5章 定时器Timer
 * 5.1.5 方法scheduleAtFixedRate(TimerTask task, Date firstTime, long period)的测试
 * Page：261
 *
 * Author：wedo
 * Time: 2018-08-18 06:42:35
 */
public class ScheduleAtFixedRateTest {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("begin timer = " + new Date());
                System.out.println("  end timer = " + new Date());
            }
        };
        System.out.println("现在执行时间：" + new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - 20);
        Date date = calendar.getTime();
        System.out.println("计划执行时间：" + date);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, date, 2000);

    }
}
