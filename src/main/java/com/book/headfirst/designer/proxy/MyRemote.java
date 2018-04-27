package com.book.headfirst.designer.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 《Head First 设计模式》编程练习
 * 第11章 代理模式
 * Page:445
 * 远程接口
 *
 * Time: 2018-04-28 06:30:08
 * Created by Cao Wei Dong on 2018-04-28.
 */
public interface MyRemote extends Remote {
    //所有的远程方法都必须声明RemoteException
    public String sayHello() throws RemoteException;
}
