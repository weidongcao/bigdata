package com.book.headfirst.designer.iterator;

import java.util.Iterator;

/**
 * 《Head First 设计模式》编程练习
 * 第9章迭代器与组合模式
 * Page: 364
 * 更新女执行的代码
 * Created by Cao Wei Dong on 2018-04-25 06:32:22
 */
public class Waitress {
    MenuComponent allMenu;

    public Waitress(MenuComponent allMenu) {
        this.allMenu = allMenu;
    }

    public void printMenu() {
        allMenu.print();
    }
    public void printVegetarianmenu() {
        Iterator iter = allMenu.createIterator();
        System.out.println("\nVEGHETARIAN MENU\n--------");
        while (iter.hasNext()) {
            MenuComponent component = (MenuComponent) iter.next();
            try {
                if (component.isVegetarian())
                    component.print();
            } catch (UnsupportedOperationException e) {

            }
        }
    }
}
