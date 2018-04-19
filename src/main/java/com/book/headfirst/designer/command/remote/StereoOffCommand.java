package com.book.headfirst.designer.command.remote;

/**
 * 《Head First 设计模式》编程练习、
 * 第6章命令模式
 * 关音响对象
 *
 * Time: 2018-04-19 07:24:25
 * Created by Cao Wei Dong on 2018-04-19.
 */
public class StereoOffCommand implements Command {
    private Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }
}
