package com.book.tk.initialization;

import com.book.tk.util.Print;

/**
 * 测试在构造器中使用this关键字调用构造器
 * Created by Cao Wei Dong on 2018-01-26.
 */
public class Flower {
    int petalCount = 0;
    String s = "initial value";

    Flower(int petals) {
        petalCount = petals;
        Print.print("Constructor w/ int arg only, petalCount= " + petalCount);
    }
    Flower(String ss){
        Print.print("Constructor w/ String arg only, s = " + ss);
        s = ss;
    }

    Flower(String s, int petals) {
        this(petals);
        this.s = s;
//        this(s);
        Print.print("String & int args");
    }

    Flower() {
        this("hi", 47);
        Print.print("default constructor (no args)");
    }

    void printPetalCount() {
        Print.print(("petalCount = " + petalCount + " s = " + s));
    }

    public static void main(String[] args) {
        Flower x = new Flower();
        x.printPetalCount();
    }
}
