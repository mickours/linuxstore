/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.remote;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.ApplicationOwner;
import com.linuxstore.ejb.entity.LinuxStoreUser;

/**
 *
 * @author mickours
 */
public interface ApplicationOwnerFacadeRemote extends AbstractFacadeRemote<ApplicationOwner> {

    public void newRelation(Application e, LinuxStoreUser user, boolean b);
    
}
