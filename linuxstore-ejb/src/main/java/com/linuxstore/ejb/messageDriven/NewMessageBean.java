/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linuxstore.ejb.messageDriven;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Mercier <michael_mercier@orange.fr>
 */
@MessageDriven(mappedName = "jms/NewMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NewMessageBean implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName = "Application-ejbPU")
    private EntityManager em;

    public NewMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
//        try {
//            if (message instanceof ObjectMessage) {
//                msg = (ObjectMessage) message;
//                UserAndApp tmp = (UserAndApp) msg.getObject();
//                Application app = tmp.app;
//                LinuxStoreUser user = tmp.user;
//                save(app);
//                app.setOwner(user);
//                save(app);
//
//                List<Application> applist = new LinkedList<Application>();
//                applist.add(app);
//                user.addToMyApplications(applist);
//                save(user);
//            }
//        } catch (JMSException e) {
//            e.printStackTrace();
//            mdc.setRollbackOnly();
//        } catch (Throwable te) {
//            te.printStackTrace();
//        }
    }

    protected void save(Object object) {
        em.persist(object);
    }

    public class UserAndApp implements Serializable {

        public UserAndApp(LinuxStoreUser user, Application app) {
            this.user = user;
            this.app = app;
        }
        public LinuxStoreUser user;
        public Application app;
    }
}
