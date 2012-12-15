/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.session;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.ApplicationOwner;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import com.linuxstore.ejb.remote.ApplicationOwnerFacadeRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author mickours
 */
@Stateless
public class ApplicationOwnerFacade extends AbstractFacade<ApplicationOwner>
    implements ApplicationOwnerFacadeRemote{
    
    @PersistenceContext(unitName = "Application-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApplicationOwnerFacade() {
        super(ApplicationOwner.class);
    }

    @Override
    public void newRelation(Application e, LinuxStoreUser user, boolean b) {
        ApplicationOwner ao = new ApplicationOwner();
        ao.setRelation(e, user, b);
        create(ao);
    }
}
