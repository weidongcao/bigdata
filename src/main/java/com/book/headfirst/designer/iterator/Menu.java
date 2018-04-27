package com.book.headfirst.designer.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 《Head First 设计模式》编程练习
 * 第9章 迭代器与组合模式
 * Page: 362
 * 实现组合菜单
 * Created by Cao Wei Dong on 2018-04-25 06:19:15
 */
public class Menu extends MenuComponent{
    ArrayList<MenuComponent> menuComponents = new ArrayList();
    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent component) {
        menuComponents.add(component);
    }

    @Override
    public void remove(MenuComponent component) {
        menuComponents.remove(component);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
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
    public void print() {
        System.out.print("\n" + getName());
        System.out.print(", " + getDescription());
        System.out.print("------------------------------------");

        Iterator iter = menuComponents.iterator();
        while (iter.hasNext()) {
            MenuComponent component = (MenuComponent) iter.next();
            component.print();
        }
    }

    @Override
    public Iterator createIterator() {
        return new CompositeIterator(menuComponents.iterator());
    }
}
