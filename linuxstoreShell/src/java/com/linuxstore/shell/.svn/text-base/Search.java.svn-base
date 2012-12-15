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
public class Search implements ShellCommand{
    
    private static ApplicationFacadeRemote applications;
    
    public Search(ApplicationFacadeRemote facade) {
        applications = facade;
    }

    @Override
    public String getName() {
        return "search";
    }

    @Override
    public String getShortDescription() {
        return "search keyword <filter> <category> - cherche les application contenant les mots cles keyword";
    }

    @Override
    public String getDescription() {
        String description = getDescription() +"\n";
        description += "Arguments :\n";
        description += "keyword - Le(s) mot(s) clé(s) de(s) application(s) à rechercher. Pensez à mettre des guillements si votre recherche contient plusieurs mots.\n";
        description += "<filter> - (optionnel) Le tri de la recherche obtenu. La liste des applications trouvées peut être triée par name ou price\n";
        description += "<category> - (optionnel) Le résultat de la recherche n'affichera que les applications de la catégorie category.";
        return description;
    }

    @Override
    public String execute(String[] params) {
        if (params.length == 0) {
            return "Syntaxe : search keyword <filter> <category>";
        }
        String chaine = params[0];
        List<Application> liste = applications.search(chaine);
        if (params.length > 1) {
            System.out.println("TRI DETECTE : "+params[1]);
            if (params[1].equals("name")) {
                System.out.println("tri par nom");
                applications.sortByName(liste);
            } else if (params[1].equals("price")) {
                System.out.println("tri par prix");
                applications.sortByPrice(liste);
            }
            if (params.length > 2) {
                try {
                    Application.Category cat = Application.Category.valueOf(params[2]);
                    liste = applications.filter(liste, cat);
                } catch (IllegalArgumentException e) {
                    return params[2] + " n'est pas une categorie";
                }
            }
        }
        String out = "";
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
