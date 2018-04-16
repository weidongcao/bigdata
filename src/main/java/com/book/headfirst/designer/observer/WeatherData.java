package com.book.headfirst.designer.observer;


import java.util.ArrayList;
import java.util.Observable;

/**
 * 《Head First 设计模式》第2章观察者模式
 *
 * Time: 2018-04-03 06:59:53
 * Author: Weidong Cao
 * Created by Cao Wei Dong on 2018-04-03.
 */
public class WeatherData extends Observable {

    private float temperature;

    private float humidity;

    private float pressure;

    public WeatherData() { }

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
