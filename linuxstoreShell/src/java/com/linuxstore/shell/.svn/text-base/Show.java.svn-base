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
public class Show implements ShellCommand {
    
    private static ApplicationFacadeRemote applications;
    
    public Show(ApplicationFacadeRemote facade) {
        applications = facade;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getShortDescription() {
        return "show <category> - Affiche toutes les applications ou les applications de la categorie category.";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription()+"\n";
        description += "Arguments :\n";
        description += "\t<category> - (optionnel) Affiche toutes les applications de la catégorie category";
        return description;
    }

    @Override
    public String execute(String[] params) {
        List<Application> liste = applications.findAll();
        String catRecherche = "applications";
        if (params.length > 0) {
            try {
                Application.Category cat = Application.Category.valueOf(params[0]);
                liste = applications.filter(liste, cat);
                catRecherche = cat.name();
            } catch (IllegalArgumentException e) {
                return params[0] + " n'est pas une categorie";
            }
        }
        String out = "Liste des " + catRecherche + " : \n";
        for (Application application : liste) {
            out += application.getName() + " (" + application.getCategory().name() + ") Prix : " + application.getPrice() + " euros\n";
            out += "\tdescription : " + application.getDescription() + "\n\n";
        }
        return out;
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.All;
    }

    
}
