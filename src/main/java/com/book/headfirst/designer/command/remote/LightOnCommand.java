package com.book.headfirst.designer.command.remote;

import com.book.headfirst.designer.command.domain.Light;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 开灯对象
 *
 * Time: 2018-04-19 06:59:46
 * Created by Cao Wei Dong on 2018-04-19.
 */
public class LightOnCommand implements Command{
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
