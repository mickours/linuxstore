/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

import com.linuxstore.ejb.entity.LinuxStoreAdmin;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.ApplicationFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreAdminFacadeRemote;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author Clement WIRTH
 */
public class Shell {

    /**
     * @param args the command line arguments
     */
    @EJB
    private static ApplicationFacadeRemote applications;
    @EJB
    private static LinuxStoreUserFacadeRemote users;
    @EJB
    private static LinuxStoreAdminFacadeRemote admins;


    private LinuxStoreUser user;
    private boolean isRunning;
    private ArrayList<ShellCommand> commands;

    public Shell() {
        isRunning = true;
        user = null;
        commands = new ArrayList<ShellCommand>();
        commands.add(new Add(applications,this));
        commands.add(new AddUser(admins, users));
        commands.add(new Clear(applications));
        commands.add(new Connect(this,admins,users));
        commands.add(new ModifyUser(this,admins, users));
        commands.add(new Remove(applications));
        commands.add(new RemoveUser(this,admins, users));
        commands.add(new Search(applications));
        commands.add(new Show(applications,this));
        commands.add(new ShowUser(admins, users));
        commands.add(new Validate(applications));
        if (admins.findAll().isEmpty()) {
            LinuxStoreAdmin root = new LinuxStoreAdmin();
            root.setLoginMail("LinuxStore");
            root.setPassword("linuxroot");
            admins.create(root);
        }
        

    }

    private void stopRunning() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
    
    public void setLinuxStoreUser(LinuxStoreUser user) {
        this.user = user;
    }
    
    public LinuxStoreUser getLinuxStoreUser() {
        return user;
    }
    
    public boolean isAdminMode() {
        return user != null && LinuxStoreAdmin.class.isInstance(user); 
    }

    public String executeCommand(String cmd, String[] params) {
        if (cmd.equals("exit")) {
            stopRunning();
            return "Fermeture du LinuxStoreShell.";
        } else if (cmd.equals("exec")) {
            if (params.length == 0) {
                return "syntaxe : exec fileName <fileName2> ...";
            } else {
                String out = "";
                for (int i = 0; i < params.length; i++) {
                    out += executeCommandFromFile(params[i]);
                }
                return out;
            }
            } else if (cmd.equals("disconnect")) {
                if (user != null) {
                    user = null;
                    return "Vous êtes déconnecté.";
                } else {
                    return "Vous n'êtes pas connecté.";
                }
        } else if (cmd.equals("help")) {
            if (params.length == 0 || params[0].equals("help")) {
                return commandList();
            } else {
                for (ShellCommand command : commands) {
                    if (command.getName().equals(params[0])) {
                        return command.getDescription();
                    }
                }
                return params[0] + " n'est pas une commande reconnue.";
            }
        } else if(cmd.equals("addUser")){
            LinuxStoreUser newUser = new LinuxStoreUser();
            newUser.setLoginMail("mic");
            newUser.setPassword("moc");
            users.create(newUser);
            return "utilisateur créé";
        } else {
            for (ShellCommand command : commands) {
                if (cmd.equals(command.getName())) {
                    AccessType userType;
                    if (user == null) {
                        userType = AccessType.All;
                    } else if (LinuxStoreAdmin.class.isInstance(user)) {
                        userType = AccessType.AdminOnly;
                    } else {
                        userType = AccessType.UserOnly;
                    }
                    if (command.getAccessType().value() > userType.value()) {
                        return "Vous n'avez pas les droits pour executer la commande " + command.getName() + ".";
                    } else {
                        return command.execute(params);
                    }
                }
            }
            return "Commande inconnue. Tapez help pour obtenir la liste des commandes.";
        }
    }

    private String commandList() {
        String out = "Liste des commandes : commande parametre <optionnel>\n";
        out += "exit - Quitte l'application.\n";
        out += "help <command> - Affiche les commandes disponibles ou les details de la commande command.\n";
        out += "exec fileName - execute les commandes contenues dans le fichier fileName\n";
        AccessType userType;
        if (user == null) {
            userType = AccessType.All;
        } else if (LinuxStoreAdmin.class.isInstance(user)) {
            userType = AccessType.AdminOnly;
            out += "diconnect - Deconnecte du compte utilisateur.\n";
        } else {
            userType = AccessType.UserOnly;
            out += "diconnect - Deconnecte du compte utilisateur.\n";
        }
        for (ShellCommand shellCommand : commands) {
            if (shellCommand.getAccessType().value() <= userType.value()) {
                out += shellCommand.getShortDescription() + "\n";
            }
        }
        return out;
    }

    public String[] getCommandString(String cmd) {
        String[] split = cmd.split(" ");
        ArrayList<String> listeComplete = new ArrayList<String>();
        String motEnCours = "";
        for (String mot : split) {
            if (mot.startsWith("\"")) {
                if (mot.endsWith("\"")) {
                    listeComplete.add(mot.substring(1, mot.length() - 1));
                } else {
                    motEnCours += mot.substring(1) + " ";
                }
            } else if (!motEnCours.equals("") && !mot.endsWith("\"")) {
                motEnCours += mot + " ";
            } else if (!motEnCours.equals("") && mot.endsWith("\"")) {
                motEnCours += mot.substring(0, mot.length() - 1);
                listeComplete.add(motEnCours);
                motEnCours = "";
            } else {
                listeComplete.add(mot);
            }
        }
        return listeComplete.toArray(new String[listeComplete.size()]);
    }

    public String executeCommandFromFile(String fileName) {
        String out = "";
        try {
            File file = new File(fileName);
            if (!file.exists() && file.length() < 0) {
                return "Ce fichier n'existe pas !";
            }
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] input = getCommandString(sc.nextLine());
                String[] arguments = {};
                if (input.length > 1) {
                    arguments = new String[input.length - 1];
                    System.arraycopy(input, 1, arguments, 0, input.length - 1);
                }
                out += executeCommand(input[0], arguments) + "\n";

            }
        } catch (FileNotFoundException ex) {
            return "Erreur lors de l'ouverture du fichier :  Ce fichier n'existe pas";
        }
        return out;

    }

    public static void main(String[] args) {
        Shell shell = new Shell();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println("<--- LinuxStore shell --->");
        while (shell.isRunning) {
            String entry ="";
            if (shell.isAdminMode()) {
                entry+= "#";
            }
            entry += "->";
            System.out.print(entry);
            String[] input = shell.getCommandString(sc.next());
            String[] arguments = {};
            if (input.length > 1) {
                arguments = new String[input.length - 1];
                System.arraycopy(input, 1, arguments, 0, input.length - 1);
            }
            System.out.println(shell.executeCommand(input[0], arguments));
        }

    }
}
