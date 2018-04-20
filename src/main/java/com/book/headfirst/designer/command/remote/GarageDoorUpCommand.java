package com.book.headfirst.designer.command.remote;


import com.book.headfirst.designer.command.domain.GarageDoor;

/**
 * 《Head First设计模式》编程练习
 * 第6章 命令模式
 * 打开车库门对象
 *
 * Time: 2018-04-19 07:13:07
 * Created by Cao Wei Dong on 2018-04-19.
 */
public class GarageDoorUpCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
