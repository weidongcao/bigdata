package com.book.headfirst.designer.command.domain;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 *
 * Time: 2018-04-19 07:02:00
 * Created by Cao Wei Dong on 2018-04-19.
 */
public class CeilingFan {
    String location = "";
    int level;
    public static final int HIGH = 2;
    public static final int MEDIUM = 1;
    public static final int LOW = 0;

    public CeilingFan(String location) {
        this.location = location;
    }

    public void high() {
        level = HIGH;
        System.out.println(location + " ceiling fan is on high");
    }

    public void medium() {
        level = MEDIUM;
        System.out.println(location + " ceiling fan is on medium");
    }

    public void low() {
        level = LOW;
        System.out.println(location + " ceiling fan is on low");
    }

    public void off() {
        level = 0;
        System.out.println(location + " ceiling fan is off");
    }

    public int getSpeed() {
        return level;
    }
}
