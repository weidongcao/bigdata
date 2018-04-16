package com.book.headfirst.designer.factory;

/**
 * 《Head First 设计模式》第4章工厂模式
 * 用工厂模式实现Pizza的生产及销售
 * <p>
 * Time: 2018-04-11 07:01:01
 * Author: Weidong Cao
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan order a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");


    }
}
