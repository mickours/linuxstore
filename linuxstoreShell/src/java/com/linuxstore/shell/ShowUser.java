/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.LinuxStoreAdmin;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.LinuxStoreAdminFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;
import java.util.List;

/**
 *
 * @author Clement WIRTH
 */
public class ShowUser implements ShellCommand{

    private static LinuxStoreAdminFacadeRemote admins;
    private static LinuxStoreUserFacadeRemote users;
    
    public ShowUser(LinuxStoreAdminFacadeRemote administrateurs,LinuxStoreUserFacadeRemote utilisateurs) {
        admins = administrateurs;
        users = utilisateurs;
    }

    @Override
    public String getName() {
        return "showuser";
    }

    @Override
    public String getShortDescription() {
        return "showuser - Affiche tous les utilisateurs.";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription()+"\n";
        description += "Exemple :\n";
        description += "\tlogin : 'Jacques' password : 'toto' - Utilisateur dont le login est 'Jacques' et le password 'toto'.\n ";
        description += "\t#ADMIN login : 'pouet pouet' password : 'moi aussi' - Admin dont le login est 'pouet pouet' et le password 'moi aussi'.\n ";
        return description;
    }

    @Override
    public String execute(String[] params) {
        List<LinuxStoreAdmin> listeAdmins = admins.findAll();
        List<LinuxStoreUser> listeUsers = users.findAll();
        String out = "Liste des utilisateurs : \n";
        for (LinuxStoreUser user : listeAdmins) {
            out += "#ADMIN login : '"+user.getLoginMail()+"' password : '"+user.getPassword()+"'\n";
        }
        for (LinuxStoreUser user : listeUsers) {
            out += "login : '"+user.getLoginMail()+"' password : '"+user.getPassword()+"'\n";
        }
        return out;
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.AdminOnly;
    }

    
}
