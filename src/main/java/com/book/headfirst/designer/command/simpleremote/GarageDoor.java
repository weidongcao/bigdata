package com.book.headfirst.designer.command.simpleremote;

import java.io.Serializable;

/**
 * Created by Cao Wei Dong on 2018-04-17.
 */
public class GarageDoor implements Serializable {
    public void up() {
        System.out.println("GarageDoor is up");
    }

    public void down() {
        System.out.println("Garage Door is Down");
    }

    public void stop() {
        System.out.println("Garage Door is stop");
    }

    public void lightOn() {
        System.out.println("Garage Door is light on");
    }
    public void lightOff() {
        System.out.println("Garage Door is light off");
    }
}
