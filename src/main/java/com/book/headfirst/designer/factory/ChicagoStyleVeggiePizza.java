package com.book.headfirst.designer.factory;

/**
 * 《Head First 设计模式》第4章工厂模式
 * 用工厂模式实现Pizza的生产及销售
 * 芝加哥风味比萨
 *
 * Time: 2018-04-11 07:01:01
 * Author: Weidong Cao
 */
public class ChicagoStyleVeggiePizza extends Pizza {
    public ChicagoStyleVeggiePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
    }

    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
