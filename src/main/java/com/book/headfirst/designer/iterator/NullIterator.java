package com.book.headfirst.designer.iterator;

import java.util.Iterator;

/**
 * 《Head First 设计模式》编程练习
 * 第9章 迭代器与组合模式
 * Page：372
 * 空迭代器
 *
 * Time: 2018-04-26 06:09:17
 * Created by Cao Wei Dong on 2018-04-26.
 */
public class NullIterator implements Iterator{
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
