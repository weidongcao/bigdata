package com.book.headfirst.designer.factory.reform;

/**
 * 《Head First 设计模式》 第4章 工厂模式
 * Page:151
 * 制造蛤蜊比萨的类
 *
 * Time: 2018-04-13 06:26:37
 * Author: wudo Cao
 */
public class ClamPizza extends Pizza{
    PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        clam = ingredientFactory.createClam();
    }
}
