package com.book.headfirst.designer.decorate;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * java.io包内用的很多的装饰模式，这是做下测试写下自己的装饰模式
 * 要实现的功能是把所有的大写字母转为小写字母
 *
 * Time:2018-04-10 05:51:49
 * Author: Weidong Cao
 * Created by Cao Wei Dong on 2018-04-10.
 */
public class LowerCaseInputStream extends FilterInputStream{
    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        return (c == -1 ? c : Character.toLowerCase((char) c));
    }
    public int read(byte[] b, int offset, int len) throws IOException {
        int result = super.read(b, offset, len);
        for (int i = 0; i < offset + len; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
