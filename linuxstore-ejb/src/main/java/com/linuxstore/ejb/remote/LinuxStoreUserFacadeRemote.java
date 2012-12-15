/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.remote;

import com.linuxstore.ejb.entity.LinuxStoreUser;
import javax.ejb.Remote;

@Remote
public interface LinuxStoreUserFacadeRemote extends AbstractFacadeRemote<LinuxStoreUser>{
    public LinuxStoreUser findByLogin(String login);
}
