package com.j2se.concurrent;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by CaoWeiDong on 2018-09-03.
 */
public class TestCopyOnWriteArrayList {
    @Test
    public void test() {
        //1.初始化CopyOnWriteArrayList
        List<Integer> tempList = Arrays.asList(new Integer[]{1, 2});
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(tempList);

        //2. 模拟多线程对list进行读和写
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new ReadThread(list));
        service.execute(new WriteThread(list));
        service.execute(new WriteThread(list));
        service.execute(new WriteThread(list));
        service.execute(new ReadThread(list));
        service.execute(new WriteThread(list));
        service.execute(new ReadThread(list));
        service.execute(new WriteThread(list));
        System.out.println("list size: " + list.size());
    }
}

class ReadThread implements Runnable {
    private List<Integer> list;

    public ReadThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (Integer ele : list) {
            System.out.println("ReadThread: " + ele);
        }
    }
}

class WriteThread implements Runnable {
    private List<Integer> list;

    public WriteThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        this.list.add(9);
    }
}
