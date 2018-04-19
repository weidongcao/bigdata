package com.book.headfirst.designer.command.remote;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 * 实现关灯的对象
 *
 * Time: 2018-04-19 06:56:30
 * Created by Cao Wei Dong on 2018-04-19.
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
