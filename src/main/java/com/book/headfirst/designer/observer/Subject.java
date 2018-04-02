package com.book.headfirst.designer.observer;

/**
 * 《Head First 设计模式》第二章 观察者模式
 * 主题接口
 *
 * Time: 2018-04-03 06:50:15
 * Author: Weidong Cao
 * Created by Cao Wei Dong on 2018-04-03.
 */
public interface Subject {
    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();

}
