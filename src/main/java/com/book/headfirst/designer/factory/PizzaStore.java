package com.book.headfirst.designer.factory;

/**
 * 《Head First 设计模式》第4章工厂模式
 * 用工厂模式实现Pizza的生产及销售
 *
 * Time: 2018-04-11 07:01:01
 * Author: Weidong Cao
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {

        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;

    }
    protected abstract Pizza createPizza (String type);
}
