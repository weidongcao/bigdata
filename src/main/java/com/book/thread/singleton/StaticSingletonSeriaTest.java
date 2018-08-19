package com.book.thread.singleton;

import java.io.*;

/**
 * 《Java多线程编程核心技术》第6章 单例模式与多线程
 * 6.4 使用静态内置类实现单例模式
 * Page：273
 * 静态内部类可以达到线程安全，但是如果遇到对象时，使用默认的方式运行得到的结果不是多例。
 * Author：wedo
 * Time: 2018-08-20 06:17:18
 */
public class StaticSingletonSeriaTest implements Serializable{
    private static final long seriaVersionUID = 888L;

    private static class MyObj{
        private static StaticSingletonSeriaTest obj = new StaticSingletonSeriaTest();
    }
    private StaticSingletonSeriaTest(){}
    public static StaticSingletonSeriaTest getInstance(){
        return MyObj.obj;
    }

    protected Object readResolve() {
        System.out.println("调用了readResolve方法！");
        return MyObj.obj;
    }
    public static void main(String[] args) throws IOException {
        try{
            StaticSingletonSeriaTest obj = MyObj.obj;
            FileOutputStream fosRef = new FileOutputStream(new File("myobj.txt"));
            ObjectOutputStream oosRef = new ObjectOutputStream(fosRef);
            oosRef.writeObject(obj);
            oosRef.close();
            fosRef.close();
            System.out.println(obj.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            FileInputStream fisRef = new FileInputStream(new File("myobj.txt"));
            ObjectInputStream iosRef = new ObjectInputStream(fisRef);
            StaticSingletonSeriaTest obj1 = (StaticSingletonSeriaTest) iosRef.readObject();
            System.out.println(obj1.hashCode());
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
