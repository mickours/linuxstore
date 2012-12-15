/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import java.util.List;

/**
 *
 * @author Clement WIRTH
 */
public class Clear implements ShellCommand{
    
    private static ApplicationFacadeRemote applications;
    
    public Clear(ApplicationFacadeRemote facade) {
        applications = facade;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getShortDescription() {
        return "clear - Supprime toutes les applications contenues dans la base de données";
    }

    @Override
    public String getDescription() {
        return getDescription();
    }

    @Override
    public String execute(String[] params) {
                List<Application> liste = applications.findAll();
        for (Application appli : liste) {
            applications.remove(appli);
        }
        return "Toute les applications viennent d'être supprimées";
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.AdminOnly;
    }
    
}
