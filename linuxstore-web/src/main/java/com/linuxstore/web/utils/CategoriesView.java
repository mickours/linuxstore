/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.web.utils;

import java.io.Serializable;

public class CategoriesView implements Serializable {

    private String name;
    private String count;

    public CategoriesView(String name, int count) {
        this.name = name;
        this.count = Integer.toString(count);
    }

    public String getName() {
        return name;
    }

    public String getCount() {
        return count;
    }

    @Override
    public String toString() {
        return name + " (" + count + ")";
    }
}
