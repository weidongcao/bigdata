package com.book.headfirst.designer.command.remote;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 *
 * Time: 2018-04-19 07:17:03
 * Created by Cao Wei Dong on 2018-04-19.
 */
public class CeilingFanOnCommand implements Command {
    private CeilingFan ceilingFan;

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        ceilingFan.high();
    }
}
