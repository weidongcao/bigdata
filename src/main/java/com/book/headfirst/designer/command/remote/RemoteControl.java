package com.book.headfirst.designer.command.remote;


/**
 * 《Head First 设计模式》第6章 命令模式
 * 实现遥控器
 *
 * Time:2018-04-19 05:26:42
 * Author: wedo
 */
public class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    //setCommand()方法必须有两个参数，分别是的位置，开的命令，关的命令，这些命令将记录在开关数组中对应的位置，
    // 以供后用
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    //当按下开或并按钮，硬件就会负责调用对应的方法，也就是onButtonWasPushed()或offButtonWasPushed()
    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n--------- Remote Control ---------\n");
        for (int i = 0; i < onCommands.length; i++) {
            sb.append("[slot " + i + "] " + onCommands[i].getClass().getSimpleName())
                    .append("   " + offCommands[i].getClass().getSimpleName() + "\n");
        }
        return sb.toString();

    }
}
