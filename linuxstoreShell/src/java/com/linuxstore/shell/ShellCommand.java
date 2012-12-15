/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

/**
 *
 * @author Clement WIRTH
 */
public interface ShellCommand {
    
    public String getName();
    
    public String getShortDescription();
    
    public String getDescription();
    
    public String execute(String[] params);
    
    public AccessType getAccessType();
    
}
