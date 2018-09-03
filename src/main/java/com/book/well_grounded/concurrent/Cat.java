package com.book.well_grounded.concurrent;

/**
 * Created by CaoWeiDong on 2018-09-03.
 */
public class Cat extends Pet {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void examine() {
        System.out.println("Meow!");
    }
}
