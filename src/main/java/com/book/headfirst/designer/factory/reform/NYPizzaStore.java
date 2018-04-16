package com.book.headfirst.designer.factory.reform;

/**
 * 《Head First 设计模式》第4章 工厂模式
 * Page: 152
 * 我们几乎完工了，只需要再到加盟店短暂巡视一下，确认他们使用了正确的比萨。
 * 也需要让他们能和本地的原料工厂搭上线。
 *
 * Time: 2018-04-13 06:33:04
 * Author: wedo Cao
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        switch (type) {
            case "cheese":
                pizza = new CheesePizza(ingredientFactory);
                break;
            case "veggie":
                pizza = new VeggiePizza(ingredientFactory);
                break;
            case "clam":
                pizza = new ClamPizza(ingredientFactory);
                break;
            case "pepperoni":
                pizza = new PepperoniPizza(ingredientFactory);
                break;
            default:
                pizza = null;
        }
        return pizza;
    }
}
