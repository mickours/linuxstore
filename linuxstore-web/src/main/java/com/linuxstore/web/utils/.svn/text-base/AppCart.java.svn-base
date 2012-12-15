/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web.utils;

import com.linuxstore.ejb.entity.Application;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
public class AppCart {
    public List<Application> appList;
    public float totalPrice;
    public AppCart() {
        this.appList = new LinkedList<Application>();
        totalPrice = 0;
    }

    public void addApp(Application appToAdd) {
        if(!appList.contains(appToAdd)){
            appList.add(appToAdd);
            totalPrice += appToAdd.getPrice();
        }
    }

    public void removeApp(Application appToRemove){
        appList.remove(appToRemove);
        totalPrice -= appToRemove.getPrice();
    }

    public float getTotalPrice(){
        return totalPrice;
    }

    public int getNumberOfApp(){
        return appList.size();
    }

    public List<Application> getAppList() {
        return appList;
    }
}
