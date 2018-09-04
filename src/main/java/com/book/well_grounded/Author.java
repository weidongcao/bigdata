package com.book.well_grounded;

/**
 * Created by CaoWeiDong on 2018-09-03.
 */
public class Author {
    private final String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author {" +
                "name='" + name + '\'' +
                '}';
    }
}
