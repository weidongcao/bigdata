package com.don.bigdata.j2se;

import java.math.BigInteger;
import java.text.NumberFormat;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class TestBigInteger {
    public static void main(String[] args) {
        BigInteger aaa = BigInteger.valueOf(-500);
        BigInteger bbb = BigInteger.valueOf(800);
        System.out.println("aaa.divide(bbb) = " + aaa.divide(bbb));
        System.out.println(aaa.subtract(bbb));

        NumberFormat numFormat = NumberFormat.getPercentInstance();
        numFormat.setMaximumFractionDigits(4);
        String result = numFormat.format(aaa.floatValue() / bbb.floatValue());
        System.out.println("result = " + result);
    }
}
