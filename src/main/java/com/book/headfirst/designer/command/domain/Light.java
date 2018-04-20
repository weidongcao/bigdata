package com.book.headfirst.designer.command.domain;

import java.io.Serializable;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 * 灯实体
 *
 * Time: 2018-04-19 06:54:03
 * Author: wedo
 */
public class Light implements Serializable {
    String location = "";

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " light is on");
    }

    public void off() {
        System.out.println(location + " light is off");
    }
}
