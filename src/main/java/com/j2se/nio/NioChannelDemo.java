package com.j2se.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Java Nio 练习
 * 参考的博客：
 * http://ifeve.com/channels/
 * Created by Cao Wei Dong on 2018-03-13 06:26:29
 */
public class NioChannelDemo {
    @Test
    public void channelPractice() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("D:\\opt\\data\\2013世界大学排行榜.tsv", "rw");

        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read: " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

    }
}
