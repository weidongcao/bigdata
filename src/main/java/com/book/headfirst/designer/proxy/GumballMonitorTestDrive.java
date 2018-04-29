package com.book.headfirst.designer.proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 《Head First 设计模式》
 * 第11章代理模式
 * page: 455
 *
 * 编写监视器测试程序
 * Time: 2018-04-28 07:26:38
 * Created by Cao Wei Dong on 2018-04-28.
 */
public class GumballMonitorTestDrive {
    public static void main(String[] args) {
        String[] location = {
                "rmi://santafe.mightygumball.com/gumballmachine",
                "rmi://boulder.mightgumball.com/gumballmachine",
                "rmi://seattle.mightygumball.com/gumballmachine"
        };

        GumballMonitor[] monitor = new GumballMonitor[location.length];
        for (int i = 0; i < location.length; i++) {
            try {
                GumballMachineRemote remote = (GumballMachineRemote) Naming.lookup(location[i]);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < monitor.length; i++) {
            monitor[i].report();
        }
    }
}
