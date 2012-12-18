/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.LinuxStoreAdmin;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Clement WIRTH
 */
public class Show implements ShellCommand {

    private static ApplicationFacadeRemote applications;
    private Shell shell;

    public Show(ApplicationFacadeRemote facade, Shell shell) {
        applications = facade;
        this.shell = shell;
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
        String description = getShortDescription() + "\n";
        description += "Arguments :\n";
        description += "\t<category> - (optionnel) Affiche toutes les applications de la catégorie category";
        return description;
    }

    @Override
    public String execute(String[] params) {
        List<Application> liste = applications.findAll();
        if (shell.getLinuxStoreUser() != null && !LinuxStoreAdmin.class.equals(shell.getLinuxStoreUser().getClass())) {
            liste = applications.filterByDisponibility(liste);
        }
        String catRecherche = "applications";
        if (params.length > 0) {
            if (params[0].equals("unvalidated") && LinuxStoreAdmin.class.equals(shell.getLinuxStoreUser().getClass())) {
                List<Application> newListe = new LinkedList<Application>(); 
                for (Application application : liste) {
                    if (!application.isValidated()) {
                        newListe.add(application);
                    }
                    liste = newListe;
                }
            } else {
                try {
                    Application.Category cat = Application.Category.valueOf(params[0]);
                    liste = applications.filter(liste, cat);
                    catRecherche = cat.name();
                } catch (IllegalArgumentException e) {
                    return params[0] + " n'est pas une categorie";
                }
            }
        }
        String out = "Liste des " + catRecherche + " : \n";
        for (Application application : liste) {
            out += application.getName() + " (" + application.getCategory().name() + ") Prix : " + application.getPrice() + " euros";
            if (!application.isValidated()) {
                out += " non validée";
            }
            out += "\n";
            out += "\tdescription : " + application.getDescription() + "\n\n";
        }
        return out;
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.All;
    }
}
