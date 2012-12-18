/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.Application.Category;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.fileupload.disk.DiskFileItem;

/**
 *
 * @author Clement WIRTH
 */
public class Add implements ShellCommand {

    public static List<String> listOfAppExtensions = Arrays.asList("zip", "gz", "tar", "7z");
    public static List<String> listOfImgExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");
    private static ApplicationFacadeRemote applications;
    private Shell shell;

    public Add(ApplicationFacadeRemote facade,Shell shell) {
        applications = facade;
        this.shell = shell;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getShortDescription() {
        return "add name category price description - ajoute une application de nom name, de categorie category, de prix price et de description description";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription()+"\n";
        description += "Arguments :\n";
        description += "\tname - Nom de l'application que l'on va ajouter. Ce nom doit être unique pour chaque application.\n";
        description += "\tcategory - La categorie de l'application. Peut prendre les valeurs";
        for (Category cat : Application.Category.values()) {
            description +=" "+cat;
        }
        description += "\n";
        description += "\tprice - Le prix de l'application. Price est un flottant.\n";
        description += "\tdescription - La description de notre application. Elle est limitee a 1024 caractères.";
        return description;
    }

    @Override
    public String execute(String[] params) {
                if (params.length < 4) {
            return "Erreur de syntaxe : add name category price url description";
        }
        String name = params[0];
        if (applications.findByName(name) != null) {
            return "Erreur : une application du meme nom existe deja";
        }
        Category cate;
        try {
            cate = Category.valueOf(params[1]);
        } catch (IllegalArgumentException e) {
            return params[1] + " n'est pas une categorie";
        }
        Float price = Float.parseFloat(params[2]);
        String description = "";
        for (int i = 3; i < params.length; i++) {
            description += params[i] + " ";
        }
        Application appli = new Application();
        appli.setName(name);
        appli.setCategory(cate);
        appli.setPrice(price);
        appli.setDescription(description);
        applications.create(appli);
        appli = applications.findByName(appli.getName());
        appli.setOwner(shell.getLinuxStoreUser());
        appli.setFile(applications.getEmptyArchive());
        applications.edit(appli);
        
        return "Application "+appli.getName()+" ajoutée";
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.UserOnly;
    }

}
