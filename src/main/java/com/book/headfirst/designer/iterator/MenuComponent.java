package com.book.headfirst.designer.iterator;

import java.util.Iterator;

/**
 * 《Head First 设计模式》编程练习
 * 第9章 迭代器感情组合模式
 * Page: 360
 * 实现菜单组件
 * 组合模式以单一责任设计原则换取透明性。什么是透明性呢？
 * 通过让组件的接口同时包含一些管理子节点和叶节点的操作，客户就可以将组合和叶节点一视同仁。
 * 也就是说，一个元素空间是组合不是叶节点，对客户是透明的。
 * Created by Cao Wei Dong on 2018-04-25 06:07:09
 */
public abstract class MenuComponent {
    public void add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    public void print() {
        throw new UnsupportedOperationException();
    }

    public Iterator createIterator() {
        throw new UnsupportedOperationException();
    }
}
