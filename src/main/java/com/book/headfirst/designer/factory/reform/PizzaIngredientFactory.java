package com.book.headfirst.designer.factory.reform;

/**
 * 《Head First 设计模式》第4章工厂模式。
 * Page：146
 * 建造原料工厂
 * 建造一个工厂来生产原料；这个工厂将负责创建原料家庭中的每一种原料。
 * 也就是说，工厂将需要生产面团、资料、芝士等。
 *
 * Time: 2018-04-13 05:29:13
 * Author: Weidong Cao
 */
public interface PizzaIngredientFactory {
    public Dough createDough();

    public Sauce createSauce();

    public Cheese createCheese();

    public Veggies[] createVeggies();

    public Pepperoni createPepperoni();

    public Clams createClam();
}
