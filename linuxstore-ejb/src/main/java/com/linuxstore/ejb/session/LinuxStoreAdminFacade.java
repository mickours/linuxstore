/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.session;

import com.linuxstore.ejb.entity.LinuxStoreAdmin;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.LinuxStoreAdminFacadeRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@Stateless
public class LinuxStoreAdminFacade extends AbstractFacade<LinuxStoreAdmin>
                                    implements LinuxStoreAdminFacadeRemote{
    @PersistenceContext(unitName = "Application-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LinuxStoreAdminFacade() {
        super(LinuxStoreAdmin.class);
    }

    @Override
    public LinuxStoreAdmin findByLogin(String login){
         for (LinuxStoreAdmin user : findAll()){
             if (user.getLoginMail().equals(login)){
                 return user;
             }
         }
         return null;
    }
}
