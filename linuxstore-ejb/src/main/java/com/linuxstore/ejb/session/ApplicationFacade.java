/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.session;

import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.Application.Category;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ApplicationException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@Stateless
public class ApplicationFacade extends AbstractFacade<Application> implements ApplicationFacadeRemote {
    @PersistenceContext(unitName = "Application-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApplicationFacade() {
        super(Application.class);
    }

    @Override
    public List<Application> filterByDisponibility(List<Application> appList) {
        List<Application> filteredList = new LinkedList<Application>();
        for (Application application : appList) {
            if (application.isValidated() && application.isAvailable()) {
                filteredList.add(application);
            }
        }
        return filteredList;
    }

    @Override
    public String getEmptyArchive() {
        File archive = new File("app-2548-556.662.zip");
        try {
            archive.createNewFile();
            return archive.getCanonicalPath();
        } catch (IOException ex) {
            throw new AppException("Problem while getting empty archive");
        }
        
    }

    @ApplicationException
    public class AppException extends RuntimeException {
        public AppException(String message) {
            super(message);
        }
    }


    @Override
    public List<Application> filter(List<Application> appList, Category categ) {
        List<Application> filteredList = new LinkedList<Application>();
        try{
            for (Application app : appList){
                if (app.getCategory().equals(categ)){
                    filteredList.add(app);
                }
            }
        } catch(Exception ex){
            throw new AppException("The filtering is impossible");
        }
        return filteredList;
    }

    /**
     * gives the count of application in this category with this patern. If the
     * patern is null or empty it gives the count for all the applications
     */
    @Override
    public int categoryCount(String patern,Category categ) {
        int nb = 0;
        List<Application> appList;
        if (patern == null || patern.isEmpty()){
            appList = findAll();
        }
        else{
            appList = search(patern);
        }
        appList = filterByDisponibility(appList);
        for (Application app : appList){
            if (categ.equals(app.getCategory())){
                nb++;
            }
        }
        return nb;
    }

    @Override
    public List<Application> sortByPrice(List<Application> appList){
        Collections.sort(appList, new Comparator<Application>() {
                    @Override
                    public int compare(Application t, Application t1) {
                        return (t.getPrice() < t1.getPrice())? -1: 1;
                    }
        });
        return appList;
    }

    @Override
    public List<Application> sortByName(List appList) {
        Collections.sort(appList, new Comparator<Application>() {
                    @Override
                    public int compare(Application t, Application t1) {
                        return t.getName().compareTo(t1.getName());
                    }
        });
        return appList;
    }



    @Override
    public List<Application> search(String patern){
        List<Application> found = new LinkedList<Application>();
        boolean match = false;
        try {
            for (Application app : findAll()){
                for (String toMatch : patern.split("\\s")){
                    if (app.toString().matches("(?i).*"+toMatch+".*") && !match){
                        found.add(app);
                        match = true;
                    }
                }
                match = false;
            }
        } catch (Exception e) {
            throw new AppException("Invalid research");
        }

        return found;
    }

    @Override
    public Application findByName(String nom){
         for (Application app : findAll()){
             if (app.getName().equals(nom)){
                 return app;
             }
         }
         return null;
    }

}
