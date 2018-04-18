package com.book.headfirst.designer.command.simpleremote;

/**
 * Created by Cao Wei Dong on 2018-04-17.
 */
public class GarageDoorCommand implements Command{
    GarageDoor door;
    @Override
    public void execute() {
        door.lightOn();
    }
}
