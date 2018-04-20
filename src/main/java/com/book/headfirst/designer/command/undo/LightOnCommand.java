package com.book.headfirst.designer.command.undo;

import com.book.headfirst.designer.command.domain.Light;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 * 实现undo()
 *
 * Time: 2018-04-20 06:43:10
 * Created by Cao Wei Dong on 2018-04-20.
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
