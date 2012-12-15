/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;

/**
 *
 * @author Clement WIRTH
 */
public class Remove implements ShellCommand {

    
    private static ApplicationFacadeRemote applications;
    
    public Remove(ApplicationFacadeRemote facade) {
        applications = facade;
    }
    
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getShortDescription() {
        return "remove name- Supprime l'application name de la base de données";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription()+"\n";
        description += "Pour supprimer une application, vous devez être son proprietaire ou administrateur.\n";
        description += "Arguments :\n";
        description += "\tname -  le nom de l'application à supprimer. ";
        return description;
    }

    @Override
    public String execute(String[] params) {
        if (params.length < 1) {
            return "Erreur de syntaxe : tapez help "+getName()+" pour plus d'information.";
        }
        String name = params[0];
        Application appli = applications.findByName(name);
        if (appli != null) {
            applications.remove(appli);
            return "L'application :" + appli.toString() + " vient d'être supprimé";
        } else {
            return "L'application " + appli.toString()+" n'est pas dans la base de données";
        }
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.AdminOnly;
    }
    
}
