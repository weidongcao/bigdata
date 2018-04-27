package com.book.headfirst.designer.iterator;

import java.util.Iterator;
import java.util.Stack;

/**
 * 《Head First 设计模式》
 * 第9章 迭代器与组合模式
 * Page: 369
 * 组合迭代器
 *
 * Time: 2018-04-25 06:56:59
 * Created by Cao Wei Dong on 2018-04-25.
 */
public class CompositeIterator implements Iterator {
    Stack stack = new Stack();

    public CompositeIterator(Iterator iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            Iterator iter = (Iterator) stack.peek();
            if (!iter.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public Object next() {
        if (hasNext()) {
            Iterator iterator = (Iterator) stack.peek();
            MenuComponent component = (MenuComponent) iterator.next();
            if (component instanceof Menu) {
                stack.push(component.createIterator());
            }
            return component;
        } else {
            return null;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
