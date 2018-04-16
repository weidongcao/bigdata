package com.book.headfirst.designer.command;

/**
 * 《Head First 设计模式》编程练习
 * 第6章命令模式
 * 设计一个遥控器，它只有一个按钮和对应的插槽，可以控制装置
 *
 * Time: 2018-04-17 06:50:59
 * Author: wedo
 */
public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl() {
    }

    public void setCommand(Command slot) {
        this.slot = slot;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
