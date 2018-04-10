package com.book.headfirst.designer.decorate;

/**
 * Created by Cao Wei Dong on 2018-04-09.
 */
public class DarkRoast extends Beverage{
    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return .99;

    }
}
