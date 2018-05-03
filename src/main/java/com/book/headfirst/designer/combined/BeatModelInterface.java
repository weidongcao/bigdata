package com.book.headfirst.designer.combined;

/**
 * 《Head First 设计模式》编程练习
 * 第12章复合模式
 * Page：537
 *
 * Time: 2018-05-03 07:04:01
 * Created by Cao Wei Dong on 2018-05-03.
 */
public interface BeatModelInterface {
    void initialize();

    void on();

    void off();

    void setBPM(int bpm);

    int getBPM();

    void registyerObserver(BeatObserver observer);

    void removeObserver(BeatObserver observer);

    void registerObserver(BPMObserver observer);

    void removeObserver(BPMObserver observer);
}
