/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.remote;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@Remote
public interface AbstractFacadeRemote<T> {
    public void create(T entity);

    public void edit(T entity);

    public void remove(T entity);

    public T find(Object id);

    public List<T> findAll();

    public List<T> findRange(int[] range);

    public int count();
}
