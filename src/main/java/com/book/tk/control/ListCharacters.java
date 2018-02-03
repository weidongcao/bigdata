package com.book.tk.control;

/**
 * 测试char类型的字符与数字对应的关系
 * Created by Cao Wei Dong on 2018-01-16.
 */
public class ListCharacters {
    public static void main(String[] args) {
        for (char i = 0; i < 200; i++) {
            if (Character.isLowerCase(i))
            System.out.println("value: " + (int) i + " character: " + i);
        }
    }
}
