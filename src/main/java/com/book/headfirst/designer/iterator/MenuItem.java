package com.book.headfirst.designer.iterator;

import java.util.Iterator;

/**
 * 《Head First 设计模式》编程练习
 * 第9章 迭代器与组合模式
 * Page: 361
 * 实现菜单项
 *
 * Created by Cao Wei Dong on 2018-04-25 06:13:58
 */
public class MenuItem extends MenuComponent{
    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print() {
        System.out.print("  " + getName());
        if (isVegetarian())
            System.out.print("(v)");
        System.out.println(",   " + getPrice());
        System.out.println("    -- " + getDescription());
    }

    @Override
    public Iterator createIterator() {
        return new NullIterator();
    }
}
