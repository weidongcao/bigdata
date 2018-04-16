package com.book.headfirst.designer.command;

/**
 * 《Head First 设计模式》第6章 命令模式
 * 实现一个打开电灯的命令。
 * 根据厂商提供的类，Light类有两个方法：on()和off()。
 *
 * Time: 2018-04-17 06:45:50
 * Author: Weiodng Cao
 */
public class LightOnCommand implements Command{
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
