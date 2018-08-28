package com.book.well_grounded;

import java.io.IOException;
import java.nio.file.*;

/**
 * 《Java程序员的修炼之道》第2章 新I/O
 * 2.4.5 文件修改通知
 * Page：36
 * 在Java7中可以用java.nio.file.WatchService类监测文件或目录的变化。
 * 该类客户线程监视注册文件或目录的变化，并且在检测到变化时返回一个事件。
 * 这种事件通知对于安全监测、属性文件中的数据刷新等很多用命都很有用。
 * 是现在某些应用程序中常用的轮询机制的理想替代品。
 *
 * Created by Cao Wei Dong on 2018-08-28 07:55.
 */
public class WatchServiceTest {
    /*
     * 在得到默认的WatchService后，将Desktop目录登记到变化监测名单中。
     * 然后在一个无限循环中执行WatcherService的take()方法，直到WatchKey的到来。
     * 一旦得到WatchKey，代码就遍历其WatchEvent进行检测。发现了类型的ENTRY_MODIFY
     * 的WatchEvent，就诏先天下目录发生了变化。重置key准备迎接下一个事件，继续等待。
     *
     * 还有其他可以监测的事件，比如ENTRY_CREATE、ENTRY_DELETE和OVERFLOW
     * @param args
     */
    public static void main(String[] args) {
        try{
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = FileSystems.getDefault().getPath("D:\\Module\\Desktop");
            WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("deskpot dir changes!");
                    }
                }
                key.reset();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
