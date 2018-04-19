package com.book.headfirst.designer.command.remote;


/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 * 打开音响的命令
 *
 * Time: 2018-04-19 05:44:43
 * Createed By wedo
 */
public class StereoOnWithCDCommand implements Command {
    private Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }
}
