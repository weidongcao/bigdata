package com.book.headfirst.designer.command.remote;

import java.io.Serializable;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 * 音响实体
 *
 * Time: 2018-04-19 05:47:56
 * Created By wedo
 */
public class Stereo implements Serializable{
    String location;

    public Stereo(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " stereo is on");
    }

    public void off() {
        System.out.println(location + " stereo is off");
    }

    public void setCD() {
        System.out.println(location + " stereo is off");
    }

    public void setDVD() {
        System.out.println(location + " stereo is set for Radio");
    }

    public void setRadio() {
        System.out.println(location + " stereo is set for Radio");
    }

    public void setVolume(int i) {
        System.out.println(location + "Stereo volume set to " + i);
    }
}
