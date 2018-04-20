package com.book.headfirst.designer.command.remote;


import com.book.headfirst.designer.command.domain.GarageDoor;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 *
 * Time: 2018-04-19 07:11:14
 * Created by Cao Wei Dong on 2018-04-19.
 */
public class GarageDoorDownCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
