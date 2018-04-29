package com.book.headfirst.designer.proxy;

import java.rmi.RemoteException;

/**
 * 《Head First 设计模式》
 * 第11章代理模式
 * Page:454
 * 对GummballMonitor进行修改使其支持代理模式
 *
 * Timr: 2018-04-28 07:23:34
 * Created by Cao Wei Dong on 2018-04-28.
 */
public class GumballMonitor {
    GumballMachineRemote machine;

    public GumballMonitor(GumballMachineRemote machine) {
        this.machine = machine;
    }

    public void report() {
        try {
            System.out.println("Gumball Machine: " + machine.getLocation());
            System.out.println("Current inventory: " + machine.getCount());
            System.out.println("Current state: " + machine.getState());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
