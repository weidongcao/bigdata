package com.book.tk.control;

import java.util.Random;

/**
 * Created by Cao Wei Dong on 2018-01-17.
 */
public class VowelsAndConsonants {
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i = 0; i < 100; i++) {
            int c = rand.nextInt(26) + 'a';
            System.out.print((char) c + ", " + c + ": ");
            switch (c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    System.out.println("vowel");
                    break;
                case 'y':
                case 'w':
                    System.out.println("SomeTimes a Vowel");
                    break;
                default:
                    System.out.println("consonant");
            }
        }
    }
}
