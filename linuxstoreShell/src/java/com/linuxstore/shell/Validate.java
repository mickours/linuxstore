/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Clement WIRTH
 */
public class Validate implements ShellCommand {

    private static ApplicationFacadeRemote applications;
    
    public Validate(ApplicationFacadeRemote apps) {
        applications = apps;
    }
    
    @Override
    public String getName() {
        return "validate";
    }

    @Override
    public String getShortDescription() {
        return "validate <name> - Valide toutes les applications ou les applications de nom <name>";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription() + "\n";
        description += "Arguments :\n";
        description += "\t<name> - (optionnel) Valide toutes les applications de nom name";
        return description;
    }

    @Override
    public String execute(String[] params) {
        List<Application> liste = new LinkedList<Application>();
        if (params.length > 0) {
            liste.add(applications.findByName(params[0]));
            if (liste.size() == 0) {
                return "Il n'y a pas d'application de nom "+params[0];
            }
        } else {
            for (Application application : applications.findAll()) {
                if (!application.isValidated()) {
                    liste.add(application);
                }
            }
        }
        for (Application application : liste) {
            application.setValidated();
            applications.edit(application);
        }
        return "Toutes les applications on été validées";
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.AdminOnly;
    }
    
}
