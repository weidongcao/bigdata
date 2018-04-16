package com.book.headfirst.designer.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 《Head First 设计模式》第2章 观察者模式
 *
 * Time: 2018-04-03 07:09:33
 * Author: Weidong Cao
 * Created by Cao Wei Dong on 2018-04-03.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement{
    Observable observable;
    private float temperature;

    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Currentk Conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}
