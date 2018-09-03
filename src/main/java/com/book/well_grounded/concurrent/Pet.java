package com.book.well_grounded.concurrent;

/**
 * Created by CaoWeiDong on 2018-09-03.
 */
public abstract class Pet {
    protected final String name;

    public Pet(String name) {
        this.name = name;
    }

    public abstract void examine();
}
