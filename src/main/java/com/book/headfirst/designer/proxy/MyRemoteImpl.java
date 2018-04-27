package com.book.headfirst.designer.proxy;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * 《Head First 设计模式》编程练习
 * 第11章代理模式
 * Page:445
 * 远程服务的实现类
 *
 * Time:2018-04-28 06:33:12
 * Created by Cao Wei Dong on 2018-04-28.
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{
    protected MyRemoteImpl() throws RemoteException { }

    //这里不需要声明RemoteException
    @Override
    public String sayHello() throws RemoteException {
        return "Server says 'Hey'";
    }

    public static void main(String[] args) {
        try {
            MyRemote service = new MyRemoteImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
