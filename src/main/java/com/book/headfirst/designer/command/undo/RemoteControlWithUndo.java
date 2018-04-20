package com.book.headfirst.designer.command.undo;


import java.util.stream.IntStream;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 * 实现undo()
 * <p>
 * Time: 2018-04-20 06:43:10
 * Created by Cao Wei Dong on 2018-04-20.
 */
public class RemoteControlWithUndo {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteControlWithUndo() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        IntStream.range(0, 7).forEach(i -> onCommands[i] = noCommand);
        IntStream.range(0, 7).forEach(i -> offCommands[i] = noCommand);

        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n------ Remote Control -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            sb.append("[slot " + i + "] " + onCommands[i].getClass().getSimpleName()
                    + "    " + offCommands[i].getClass().getSimpleName() + "\n");
        }
        sb.append("[undo] " + undoCommand.getClass().getSimpleName() + "\n");
        return sb.toString();
    }

}
