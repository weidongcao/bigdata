package com.book.headfirst.designer.command.undo;

import com.book.headfirst.designer.command.domain.Light;
import org.junit.Test;

/**
 * 《Head First 设计模式》编程练习
 * 第6章 命令模式
 * 实现undo()
 * <p>
 * Time: 2018-04-21 06:25:49
 * Author: wedo
 */
public class RemoteLoader {
    @Test
    public void doTest() {
        RemoteControlWithUndo control = new RemoteControlWithUndo();

        Light livingRoomLight = new Light("Living Room");
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        control.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        control.onButtonWasPushed(0);
        control.offButtonWasPushed(0);

    }
}
