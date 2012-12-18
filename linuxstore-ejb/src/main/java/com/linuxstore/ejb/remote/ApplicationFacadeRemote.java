/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.remote;

import com.linuxstore.ejb.entity.Application;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Clement WIRTH
 */
@Remote
public interface ApplicationFacadeRemote extends AbstractFacadeRemote<Application> {

    public List<Application> filter(List<Application> appList, Application.Category categ);

    public List<Application> filterByDisponibility(List<Application> appList);
    
    public int categoryCount(String patern,Application.Category categ);

    public List<Application> sortByPrice(List<Application> appList);

    public List<Application> sortByName(List<Application> appList);

    public List<Application> search(String patern);

    public Application findByName(String nom);

}
