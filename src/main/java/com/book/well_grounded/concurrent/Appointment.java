package com.book.well_grounded.concurrent;

/**
 * Created by CaoWeiDong on 2018-09-03.
 */
public class Appointment<T> {
    private final T toBeSeen;

    public T getPatient(){
        return toBeSeen;
    }

    public Appointment(T toBeSeen) {
        this.toBeSeen = toBeSeen;
    }
}
