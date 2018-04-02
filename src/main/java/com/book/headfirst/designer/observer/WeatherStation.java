package com.book.headfirst.designer.observer;

/**
 * 《Head First设计模式》第2章 观察者模式
 *
 * Time: 2018-04-03 07:22:04
 * Author: Weidong Cao
 * Created by Cao Wei Dong on 2018-04-03.
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);

//        StatisticD
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 28.2f);
    }
}
