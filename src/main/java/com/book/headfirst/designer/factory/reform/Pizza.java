package com.book.headfirst.designer.factory.reform;

/**
 * 《Head First 设计模式》 第4章 工厂模式
 * Page：149
 * 重做比萨，工厂已经一切就绪，准备生产高质量原料了；现在我们只需要重做比萨，
 * 好让它们只使用工厂生产出来的原料
 *
 * Time: 2018-04-13 05:52:20
 * Author: wedo
 */
public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clam;

    abstract void prepare();

    void bake(){
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut(){
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box(){
        System.out.println("Place pizza in official PizzaStore box");
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }


}
