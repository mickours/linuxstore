/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.shell;

/**
 *
 * @author Clement WIRTH
 */
public enum AccessType {

    All(10), UserOnly(20), AdminOnly(30);
    
    private int value;

    AccessType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
