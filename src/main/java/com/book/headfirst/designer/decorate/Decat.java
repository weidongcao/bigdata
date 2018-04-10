package com.book.headfirst.designer.decorate;

/**
 * Created by Cao Wei Dong on 2018-04-09.
 */
public class Decat extends Beverage{
    public Decat() {
        description = "Decat Coffee";
    }

    @Override
    public double cost() {
        return 1.05;

    }
}
