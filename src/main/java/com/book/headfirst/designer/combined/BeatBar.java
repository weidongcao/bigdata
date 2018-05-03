package com.book.headfirst.designer.combined;

import javax.swing.*;

/**
 * 《Head First 设计模式》编程练习
 * 第12章 复合模式
 * Page: 534
 * 利用MVC控制DJ节拍
 * Time: 2018-05-03 06:57:04
 * Created by Cao Wei Dong on 2018-05-03.
 */
public class BeatBar extends JProgressBar implements Runnable{
    JProgressBar bar;
    Thread thread;

    public BeatBar() {
        thread = new Thread(this);
        setMaximum(100);
        thread.start();
    }

    @Override
    public void run() {
        for (;;) {
            int value = getValue();
            value = (int) (value * .75);
            setValue(value);
            repaint();
            try {
                Thread.sleep(50);
            } catch (Exception e) { }
        }
    }
}
