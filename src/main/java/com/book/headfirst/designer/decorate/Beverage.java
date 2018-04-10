package com.book.headfirst.designer.decorate;

/**
 * Created by Cao Wei Dong on 2018-04-09.
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
