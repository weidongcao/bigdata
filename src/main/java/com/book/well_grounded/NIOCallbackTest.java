package com.book.well_grounded;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 《Java程序员的修炼之道》第2章 新I/O
 * 2.5.2 回调式
 * Page:41
 * 与将来式相反，回调式（Callback）所采用的事件处理技术类似于在Swing UI编程
 * 时采用的机制。其基本思想是主线程会派一个侦查员CompletionHandler到独立的
 * 线程中执行I/O操作。这个侦查员将带着I/O操作的结果返回到主线程中，这个结果
 * 会触发它自己的Completed或Failed方法（你会重写这两个方法）
 *
 * 在异步事件刚一成功或失败并需要马上采取行动时，一般会用回调式。比如在读取
 * 对盈利计算业务处理至关重要的金融数据时，如果读取失败了，你最好马上就执行
 * 回滚操作，或进行异常处理。
 * 在异步I/O活动结束后，接口java.nio.channels.CompletionHandler<V,A>会被调用，
 * 其中V是结果类型，A是提供结果的附着对象。此时必须已经有了该接口的Completed(V,A>
 * 和failed(V,A)方法的袜，你的程序才能知道在异步I/O操作成功完成或因某些原因失败时该
 * 如何处理。
 *
 * 在下例中，你将从foobar.txt文件中读取了100 000字节的数据，用CompletionHandler<Integer,ByteBuffer>
 * 声明是成功或是失败。
 * Created by CaoWeiDong on 2018-08-31.
 */
public class NIOCallbackTest {
    public static void main(String[] args) {
        try{
            Path file = Paths.get("C:\\Windows\\System32\\drivers\\etc\\hosts");
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
            ByteBuffer buffer = ByteBuffer.allocate(100_000);
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>(){
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("Bytes read [" + result + "]");
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println(exc.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
