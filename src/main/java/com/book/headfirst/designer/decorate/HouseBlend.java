package com.book.headfirst.designer.decorate;

/**
 * Created by Cao Wei Dong on 2018-04-09.
 */
public class HouseBlend extends Beverage{
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
