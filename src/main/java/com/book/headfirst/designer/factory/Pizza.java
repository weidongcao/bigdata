package com.book.headfirst.designer.factory;

import java.util.ArrayList;

/**
 * 《Head First设计模式》第4章 工厂模式
 * 用工厂模式实现比萨类
 * <p>
 * Time: 2018-04-11 06:53:00
 * Author: Weidong Cao
 */
public abstract class Pizza {
    String name;
    //面团
    String dough;
    //酱料
    String sauce;
    //一套佐料
    ArrayList toppings = new ArrayList<>();

    void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough ...");
        System.out.println("Adding sauce ...");
        System.out.println("Adding toppings: ");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println("    " + toppings.get(i));
        }
    }

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore Box");
    }

    public String getName() {
        return name;
    }


}
