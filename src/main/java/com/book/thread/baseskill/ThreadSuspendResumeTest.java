package com.book.thread.baseskill;

/**
 * 《Java多线程编程核心技术》第1章Java多线程技能
 * 1.8.3 suspend与resume方法的缺点——不同步
 * Page：41
 * 在使用suspend与resume方法时也容易出现因为线程的暂停而导致数据不同步的情况。
 *
 * Author:wedo
 * Time:2018-07-24 06:53:52
 */
public class ThreadSuspendResumeTest {
    public static void main(String[] args) throws InterruptedException {
        final MyObject obj = new MyObject();
        Thread thread1 = new Thread(() -> obj.setValue("a", "AAA"));
        thread1.setName("a");
        thread1.start();
        thread1.sleep(500);
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                obj.printUsernamePassword();
            }
        };
        thread2.start();

    }
}
class MyObject{
    private String username = "1";
    private String password = "11";
    public void setValue(String u, String p){
        this.username = u;
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("停止a线程");
            Thread.currentThread().suspend();
        }
        this.password = p;
    }
    public void printUsernamePassword(){
        System.out.println(username + "  " + password);
    }
}
