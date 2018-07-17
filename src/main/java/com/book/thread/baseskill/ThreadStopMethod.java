package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》第1章 Java多线程技能
 * 1.7.7 释放锁的不良后果
 * Page：34
 * 方法stop已经作废，因为如果强制让线程停止则有可能使一些清理性的工作得不到完成。
 * 另外一个情况就是对锁定的对象进行了"解锁"，导致数据得不到同步的处理，出现数据不一致
 * 的问题。如果出现这样的情况，程序处理的数据就有可能遭到破坏，最终导致程序执行的流程错误。
 * #Author wedo
 * #Time 2018-07-17 07:18:40
 */
public class ThreadStopMethod extends Thread{
    private User user;

    public ThreadStopMethod(User user) {
        super();
        this.user = user;
    }

    @Override
    public void run() {
        user.printString("b", "bb");
    }

    public static void main(String[] args) {
        try {
            User user1 = new User();
            ThreadStopMethod thread = new ThreadStopMethod(user1);
            thread.start();
            Thread.sleep(500);
            thread.stop();
            System.out.println(user1.getUsername() + " --> " + user1.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class User{
    private String username = "a";
    private String password = "aa";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    synchronized public void printString(String username, String password) {
        try{
            this.username = username;
            Thread.sleep(100000);
            this.password = password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
