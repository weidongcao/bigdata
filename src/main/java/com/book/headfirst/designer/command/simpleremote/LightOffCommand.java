package com.book.headfirst.designer.command.simpleremote;

/**
 * 《Head First 设计模式》第6章 命令模式
 * 关灯的命令
 *
 * Time: 2018-04-19 05:39:58
 * Author: wedu
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
