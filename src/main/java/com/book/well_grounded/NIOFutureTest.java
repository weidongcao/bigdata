package com.book.well_grounded;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 《Java程序员的修炼之道》第2章 新I/O
 * 2.5.1 将来式
 * Page :39
 *
 * 通常会用Future get()方法（带或不带超时参数）在异步I/O操作完成时获取其结果。假设你要从硬盘上的
 * 文件里读取10000000字节，在旧版本的Java中，你需要等待数据读取完成（除非你实现了一个线程池而且
 * 工作线程使用java.util.concurrent技术，这可不是件轻松的事儿）。而在Java7中，主线程可以在读取
 * 数据的同时继续完成其他工作。
 *
 *
 * @Author wedo
 * @date 2018-08-31
 */
public class NIOFutureTest {

    /*
     * 在这里用isDone()手工判定result是否结束。通常情况下，result或结束或等等后台I/O完成。
     * 这个究竟；是怎么实现的呢？
     * API/JVM为执行这个任务创建了线程池和通道组。 AsynchronourFileChannel会关联线程池，它的
     * 任务是接收I/O处理事件，并颁发给负责处理通道中I/O操作结果的处理器。跟通道中发起的I/O操作关联的
     * 结果处理器确保是由纯种池中的某个线程产生的。
     *
     */
    public static void main(String[] args) {
        try{
            Path file = Paths.get("/opt/software");
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
            ByteBuffer buffer = ByteBuffer.allocate(100_000);
            Future<Integer> result = channel.read(buffer, 0);
            while (!result.isDone()) {
                //do something else
            }
            Integer bytesRead = result.get();
            System.out.println("Bytes read [" + bytesRead + "]");
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
