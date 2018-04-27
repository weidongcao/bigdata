package com.book.headfirst.designer.iterator;

/**
 * 《Head First 设计模式》
 * 第9章 迭代器与组合模式
 * Page: 365
 * 测试程序
 *
 * Time: 2018-04-25 06:34:42
 * Created by Cao Wei Dong on 2018-04-25.
 */
public class MenuTestDrive {
    public static void main(String[] args) {
        MenuComponent pancakeHouseMenu = new Menu("Pancake House Menu", "Breakfast");
        MenuComponent dinerMenu = new Menu("Diner Menu", "Lunch");
        MenuComponent cafeMenu = new Menu("Cafe Menu", "Dinner");
        MenuComponent dessertMenu = new Menu("Dessert Menu", "Dessert Of Course");

        MenuComponent allMenus = new Menu("All Menus", "All Menus Combined");

        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        dinerMenu.add(new MenuItem(
                "Apsta",
                "Spaghetti with Marinara Sauce, and a slice of sourdough breed",
                true,
                3.89
                ));

        dinerMenu.add(dessertMenu);

        dessertMenu.add(new MenuItem(
                "apple Pie",
                "apple pie with a flakey crust, topped with vanilla ice cream",
                true,
                1.59
        ));

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();
        waitress.printVegetarianmenu();
    }
}
