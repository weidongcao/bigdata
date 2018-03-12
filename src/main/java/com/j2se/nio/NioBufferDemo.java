package com.j2se.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Java Nio Buffer练习
 * 参考博客：
 * http://ifeve.com/buffers/
 *
 * 使用Buffer读写数据一般遵循以下4个步骤：
 *      1. 写入数据到Buffer
 *      2. 调用flip()方法
 *      3. 从Buffer中读取数据
 *      4. 调用clear()方法或compact()方法
 *
 *  当向Buffer写入数据时，Buffer会记录下写了多少数据。一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。
 *  在读模式下可以读取之前写入到Buffer的所有数据
 *
 *  一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：
 *      1. 调用clear()方法，它会清空缓冲区
 *      2. 调用compact()方法，它只会清除已经读取过的数据，任何未读的数据都被移到缓冲区的起始处，
 *      新写入的数据将放到缓冲区未读数据的后面。
 * Created by Cao Wei Dong on 2018-03-13 06:57:52
 */
public class NioBufferDemo {
    @Test
    public void bufferPractice() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("D:\\opt\\data\\2013世界大学排行榜.tsv", "rw");
        //创建Channel
        FileChannel inChannel = aFile.getChannel();
        //创建Buffer
        ByteBuffer buf = ByteBuffer.allocate(48);
        //读取Buffer
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
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
