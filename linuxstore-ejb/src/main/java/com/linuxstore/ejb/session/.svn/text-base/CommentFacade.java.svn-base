/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.session;

import com.linuxstore.ejb.entity.Comment;
import com.linuxstore.ejb.remote.CommentFacadeRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> implements CommentFacadeRemote{
    @PersistenceContext(unitName = "Application-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentFacade() {
        super(Comment.class);
    }

}
