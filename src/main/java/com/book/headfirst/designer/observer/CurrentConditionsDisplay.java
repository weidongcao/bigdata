package com.book.headfirst.designer.observer;

/**
 * 《Head First 设计模式》第2章 观察者模式
 *
 * Time: 2018-04-03 07:09:33
 * Author: Weidong Cao
 * Created by Cao Wei Dong on 2018-04-03.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement{
    private float temperature;

    private float humidity;

    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public void display() {
        System.out.println("Currentk Conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.humidity = humidity;
        this.temperature = temp;
        display();
    }
}
