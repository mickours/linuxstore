/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.LinuxStoreAdminFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;

/**
 *
 * @author Clement WIRTH
 */
public class Connect implements ShellCommand {

    private Shell shell;
    private static LinuxStoreUserFacadeRemote users;
    private static LinuxStoreAdminFacadeRemote admins;

    public Connect(Shell shell, LinuxStoreAdminFacadeRemote administrateurs, LinuxStoreUserFacadeRemote utilisateurs) {
        this.shell = shell;
        users = utilisateurs;
        admins = administrateurs;
    }

    @Override
    public String getName() {
        return "connect";
    }

    @Override
    public String getShortDescription() {
        return "connect login password - Se connecte a l'utilisateur login";
    }

    @Override
    public String getDescription() {
        String description = getShortDescription() + "\n";
        description += "Arguments :\n";
        description += "login - Nom de votre compte utilisateur ou administrateur\n";
        description += "password - Le mot de passe de votre compte utilisateur ou administrateur";
        return description;
    }

    @Override
    public String execute(String[] params) {
        if (params.length < 2) {
            return "Erreur de syntaxe, tapez help connect pour plus d'information.";
        }
        String userName = params[0];
        String password = params[1];
        for (int i = 2; i < params.length; i++) {
            password += " " + params[i];
        }
        LinuxStoreUser user;
        user = admins.findByLogin(userName);
        if (user == null) {
            user = users.findByLogin(userName);
        }
        if (user == null) {
            return "Login inconnu";
        } else {
            if (user.getPassword().equals(password)) {
                shell.setLinuxStoreUser(user);
                return "Vous êtes connecté au compte " + user.getLoginMail();
            } else {
                return "Mot de passe incorrect.";
            }
        }
    }

    @Override
    public AccessType getAccessType() {
        return AccessType.All;
    }
}
