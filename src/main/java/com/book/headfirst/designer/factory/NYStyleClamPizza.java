package com.book.headfirst.designer.factory;

/**
 * 《Head First 设计模式》第4章工厂模式
 * 用工厂模式实现Pizza的生产及销售
 * 纽约风味蛤蜊比萨
 *
 * Time: 2018-04-11 07:01:01
 * Author: Weidong Cao
 */
public class NYStyleClamPizza extends Pizza {
    public NYStyleClamPizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Crated Reggiano Cheese");
    }

}
