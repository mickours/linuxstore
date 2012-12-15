/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@Entity
public class ApplicationOwner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    Application app;
    LinuxStoreUser user;
    boolean isAuthor;
    
    public void setRelation(Application app, LinuxStoreUser user, boolean isAuthor){
        this.app = app;
        this.user = user;
        this.isAuthor = isAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationOwner)) {
            return false;
        }
        ApplicationOwner other = (ApplicationOwner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.linuxstore.ejb.ApplicationOwner[ id=" + id + " ]";
    }

}
