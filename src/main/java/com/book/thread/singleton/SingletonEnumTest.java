package com.book.thread.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 《Java多线程编程核心技术》第6章单例模式与多线程
 * 6.6 使用enum枚举数据类型实现单例模式
 * Page：276
 * 枚举enum和静态代码块的特性相似，在使用枚举类时，构造方法会被自动调用，也可以
 * 应用其这个特性实现单例设计模式。
 * Author：wedo
 * Time:2018-08-20 06:41:35
 */
public enum SingletonEnumTest {
    connectionFactory;
    private Connection connection;
    private SingletonEnumTest(){
        try{
            System.out.println("调用了类的构造方法");
            String url = "jdbc:mysql://192.168.1.101:3306/hive";
            String username = "root";
            String passwd = "123123";
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, passwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(SingletonEnumTest.connectionFactory.getConnection().hashCode());
                }
            }
        };
        Thread ta = new Thread(runnable);
        Thread tb = new Thread(runnable);
        Thread tc = new Thread(runnable);
        ta.start();
        tb.start();
        tc.start();

    }
}
