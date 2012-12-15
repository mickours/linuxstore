/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.LinuxStoreAdmin;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreAdminFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;

/**
 *
 * @author Clement WIRTH
 */
public class RemoveUser implements ShellCommand {

    private Shell shell;
    private static LinuxStoreAdminFacadeRemote admins;
    private static LinuxStoreUserFacadeRemote users;
    
    public RemoveUser(Shell shell,LinuxStoreAdminFacadeRemote administrateurs,LinuxStoreUserFacadeRemote utilisateurs) {
        this.shell = shell;
        admins = administrateurs;
        users = utilisateurs;
    }
    
    @Override
    public String getName() {
        return "removeuser";
    }

    @Override
    public String getShortDescription() {
        return "removeuser name- Supprime l'utilisateur name de la base de données";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription()+"\n";
        description += "Pour supprimer un utilisateur, vous devez être administrateur.\n";
        description += "Arguments :\n";
        description += "\tname -  le login de l'utilisateur à supprimer. ";
        return description;
    }

    @Override
    public String execute(String[] params) {
        if (params.length < 1) {
            return "Erreur de syntaxe : tapez help "+getName()+" pour plus d'information.";
        }
        String name = params[0];
        LinuxStoreUser userToDelete = admins.findByLogin(name);
        if (userToDelete != null) {
            admins.remove((LinuxStoreAdmin)userToDelete);
            if (userToDelete.equals(shell.getLinuxStoreUser())) {
               shell.setLinuxStoreUser(null); 
            }
            return "L'utilisateur "+name+" a été supprimé.";    
        }
        userToDelete = users.findByLogin(name);
        if (userToDelete != null) {
            if (userToDelete.equals(shell.getLinuxStoreUser())) {
               shell.setLinuxStoreUser(null); 
            }
            users.remove(userToDelete);
            return "L'utilisateur "+name+" a été supprimé.";
        } else {
            return "Login inconnu.";
        }
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.AdminOnly;
    }
    
}
