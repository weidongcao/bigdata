package com.book.headfirst.designer.proxy;

import com.book.headfirst.designer.state.State;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 《Head First 设计模式》
 * 第11章代理模式
 * Page:452
 * GumballMachine实现GumballMachineRemote接口
 * Created by Cao Wei Dong on 2018-04-28.
 */
public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote{
    int count;
    State state;
    String location;

    protected GumballMachine() throws RemoteException {}

    public GumballMachine(String location, int numberGumballs) throws RemoteException {

    }


    @Override
    public int getCount() throws RemoteException {
        return count;
    }

    @Override
    public String getLocation() throws RemoteException {
        return location;
    }

    @Override
    public State getState() throws RemoteException {
        return state;
    }
}

