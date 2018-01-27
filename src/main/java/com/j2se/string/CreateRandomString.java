package com.j2se.string;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Cao Wei Dong on 2017-05-14.
 */
public class CreateRandomString {
    public static void main(String[] args) {
        System.out.println("randomAlphanumeric : " + RandomStringUtils.randomAlphanumeric(9));
        System.out.println("randomAlphabetic : " + RandomStringUtils.randomAlphabetic(9));

    }
}
