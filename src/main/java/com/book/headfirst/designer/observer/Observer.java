package com.book.headfirst.designer.observer;

/**
 * 《Head First 设计模式》第2章 观察者模式
 *
 * Time: 2018-04-03 06:52:18
 * Author: Weidong Cao
 * Created by Cao Wei Dong on 2018-04-03.
 */
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}
