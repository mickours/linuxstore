/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.session;

import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.LinuxStoreUserFacadeRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@Stateless
public class LinuxStoreUserFacade extends AbstractFacade<LinuxStoreUser>
                                    implements LinuxStoreUserFacadeRemote {

    @PersistenceContext(unitName = "Application-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LinuxStoreUserFacade() {
        super(LinuxStoreUser.class);
    }

    @Override
    public LinuxStoreUser findByLogin(String login){
         for (LinuxStoreUser user : findAll()){
             if (user.getLoginMail().equals(login)){
                 return user;
             }
         }
         return null;
    }
}
