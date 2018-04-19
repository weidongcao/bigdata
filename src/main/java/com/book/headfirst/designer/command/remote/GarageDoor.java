package com.book.headfirst.designer.command.remote;

import java.io.Serializable;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 * 车库门对象
 *
 * Time: 2018-04-19 07:08:09
 * Created by Cao Wei Dong on 2018-04-19.
 */
public class GarageDoor implements Serializable{
    String location;

    public GarageDoor(String location) {
        this.location = location;
    }

    public void up() {
        System.out.println(location + " garage Door is up");
    }

    public void down() {
        System.out.println(location + " garage Door is Down");
    }

    public void stop() {
        System.out.println(location + " garage Door is Stopped");
    }

    public void lightOn() {
        System.out.println(location + " garage light is on");
    }

    public void lightOff() {
        System.out.println(location + " garage light is off");
    }
}
