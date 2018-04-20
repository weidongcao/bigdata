package com.book.headfirst.designer.command.simpleremote;

import com.book.headfirst.designer.command.domain.Light;

/**
 * 《Head First 设计模式》编程练习
 * 第6章命令模式
 * 遥控器使用的简单测试
 *
 * Time: 2018-04-17 06:53:28
 * Author: wedo
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light("alksdjf");
        LightOnCommand lightOn = new LightOnCommand(light);

        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }
}
