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
public class ModifyUser implements ShellCommand {

    private Shell shell;
    private static LinuxStoreAdminFacadeRemote admins;
    private static LinuxStoreUserFacadeRemote users;

    public ModifyUser(Shell shell,LinuxStoreAdminFacadeRemote administrateurs, LinuxStoreUserFacadeRemote utilisateurs) {
        this.shell = shell;
        admins = administrateurs;
        users = utilisateurs;
    }

    @Override
    public String getName() {
        return "modifyuser";
    }

    @Override
    public String getShortDescription() {
        return "modifyuser name typeAccess <password> - Modifie l'utilisateur de nom name";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription() + "\n";
        description += "Arguments :\n";
        description += "\tname -  le login de l'utilisateur à modfifier.\n ";
        description += "\t typeAccess - (Optionnel) le type de compte de l'utilisateur (admin pour administrateur ou user pour simple client).\n";
        description += "\t <password> - (Optionnel) le nouveau mot de passe de l'utilisateur.\n";
        return description;
    }

    @Override
    public String execute(String[] params) {
        if (params.length < 2) {
            return "Erreur de syntaxe : tapez help " + getName() + " pour plus d'information.";
        }
        String login = params[0];
        String typeAccess = params[1];
        AccessType typeOfUser = AccessType.UserOnly;
        LinuxStoreUser userToModify = admins.findByLogin(login);
        if (userToModify != null) {
            typeOfUser = AccessType.AdminOnly;
        } else {
            userToModify = users.findByLogin(login);
            if (userToModify == null) {
                return "Login inconnu.";
            }
        }
        if (typeAccess.equals("admin")) {
            if (typeOfUser == AccessType.UserOnly) {
                users.remove(userToModify);
                LinuxStoreAdmin newAdmin = new LinuxStoreAdmin();
                newAdmin.setLoginMail(userToModify.getLoginMail());
                newAdmin.setPassword(userToModify.getPassword());
                admins.create(newAdmin);
                /*Récupération de l'id*/
                userToModify = admins.findByLogin(newAdmin.getLoginMail());
                typeOfUser = AccessType.AdminOnly;
            }
        } else if (typeAccess.equals("user")) {
            if (typeOfUser == AccessType.AdminOnly) {
                admins.remove((LinuxStoreAdmin) userToModify);
                LinuxStoreUser newUser = new LinuxStoreUser();
                newUser.setLoginMail(userToModify.getLoginMail());
                newUser.setPassword(userToModify.getPassword());
                users.create(newUser);
                /*Récupération de l'id*/
                userToModify = users.findByLogin(newUser.getLoginMail());
                typeOfUser = AccessType.UserOnly;
            }
        } else {
            return "Type de compte inconnu.";
        }
        if (params.length >= 3) {
            String password = params[2];
            for (int i = 3; i < params.length; i++) {
                password += " " + params[i];
            }
            userToModify.setPassword(password);
            if (typeOfUser == AccessType.AdminOnly) {
                admins.edit((LinuxStoreAdmin)userToModify);
            } else {
                users.edit(userToModify);
            }
        }
        if (userToModify.getLoginMail().equals(shell.getLinuxStoreUser().getLoginMail())) {
            shell.setLinuxStoreUser(userToModify);
        }
        return "Modifications sauvegardées.";

    }

    @Override
    public AccessType getAccessType() {
        return AccessType.AdminOnly;
    }
}
