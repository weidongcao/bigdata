package com.book.well_grounded.concurrent;

/**
 * Created by CaoWeiDong on 2018-09-03.
 */
public class Dog extends Pet {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void examine() {
        System.out.println("Woof!");
    }
}
