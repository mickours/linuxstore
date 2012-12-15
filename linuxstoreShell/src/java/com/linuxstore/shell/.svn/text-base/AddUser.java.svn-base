/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.LinuxStoreAdmin;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.LinuxStoreAdminFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;

/**
 *
 * @author Clement WIRTH
 */
public class AddUser implements ShellCommand{
    
    private static LinuxStoreAdminFacadeRemote admins;
    private static LinuxStoreUserFacadeRemote users;
    
    public AddUser(LinuxStoreAdminFacadeRemote admins,LinuxStoreUserFacadeRemote users) {
        this.admins = admins;
        this.users = users;
    }

    @Override
    public String getName() {
        return "adduser";
    }

    @Override
    public String getShortDescription() {
        return "adduser UserName Password <admin> - Ajoute le client Username avec le password Password";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription()+"\n";
        description+= "Arguments :\n";
        description += "\tUsername - Le login du nouvel utilisateur\n";
        description += "\tPassword - Le mot de passe du nouvel utilisateur\n";
        description+= "\t<admin> - (Optionnel) si le champ est a 'admin', alors cette utilisateur aura les droits admins";
        return description;
    }

    @Override
    public String execute(String[] params) {
        if (params.length < 2) {
            return "Erreur de syntaxe, tapez help adduser pour plus d'information.";
        }
        String userName = params[0];
        String password = params[1];
        String admin = "";
        if (params.length >=3) {
            admin = params[2];
        }
        if(users.findByLogin(userName)!= null || admins.findByLogin(userName)!=null) {
            return "Ce login est déjà utilisé";
        }
        if (admin.equals("admin")) {
            LinuxStoreAdmin myNewAdmin = new LinuxStoreAdmin();
            myNewAdmin.setLoginMail(userName);
            myNewAdmin.setPassword(password);
            admins.create(myNewAdmin);
            return "Nouvel admin ajouté.";
        } else {
            LinuxStoreUser myNewUser = new LinuxStoreUser();
            myNewUser.setLoginMail(userName);
            myNewUser.setPassword(password);
            users.create(myNewUser);
            return "Nouvel utilisateur ajouté.";
        }
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.AdminOnly;
    }
    
}
