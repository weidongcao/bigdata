package com.book.headfirst.designer.proxy;

import com.book.headfirst.designer.state.State;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 《Head First 设计模式》
 * 第11章代理模式
 * Page:451
 * 实现糖果店远程代理
 *
 * Time: 2018-04-28 06:58:01
 * Created by Cao Wei Dong on 2018-04-28.
 */
public interface GumballMachineRemote extends Remote {
    public int getCount() throws RemoteException;

    public String getLocation() throws RemoteException;

    public State getState() throws RemoteException;
}
