package com.book.headfirst.designer.factory.reform;

/**
 * 《Head First 设计模式》第4章 工厂模式
 * Page:150
 * 其实我们不需要设计两个不再的类来处理不同风味的比萨，让原料工厂处理这种区域差异就可以了。
 *
 * Time: 2018-04-13 05:57:57
 * Author: wedo Cao
 */
public class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}
