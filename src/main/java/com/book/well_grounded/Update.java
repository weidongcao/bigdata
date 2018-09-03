package com.book.well_grounded;

/**
 * Created by Cao Wei Dong on 2018-09-03 08:37.
 */
public class Update {
    private final Author author;
    private final String updateText;

    private Update(Builder b_) {
        author = b_.author;
        updateText = b_.updateText;
    }

    public static class Builder implements ObjBuilder<Update> {
        private Author author;
        private String updateText;

        public Builder author(Author author_) {
            author = author_;
            return this;
        }

        public Builder updateText(String updateText_) {
            updateText = updateText_;
            return this;
        }
        public Update build(){
            return new Update(this);
        }
    }
}
interface ObjBuilder<T>{
    T build();
}
class Author{

}
