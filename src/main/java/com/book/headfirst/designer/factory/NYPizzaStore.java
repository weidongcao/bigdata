package com.book.headfirst.designer.factory;

/**
 * Created by Cao Wei Dong on 2018-04-11.
 */
public class NYPizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new NYStyleCheesePizza();
                break;
            case "veggie":
                pizza = new NYStyleVeggiePizza();
                break;
            case "clam":
                pizza = new NYStyleClamPizza();
                break;
            case "pepperoni":
                pizza = new NYStylePepperoniPizza();
                break;
            default:
                pizza = null;
        }
        return pizza;
    }
}
