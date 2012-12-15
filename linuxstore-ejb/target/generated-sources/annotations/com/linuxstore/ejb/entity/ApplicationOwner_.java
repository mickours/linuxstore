package com.linuxstore.ejb.entity;

import com.linuxstore.ejb.entity.Application;
import com.linuxstore.ejb.entity.LinuxStoreUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-11-23T08:49:50")
@StaticMetamodel(ApplicationOwner.class)
public class ApplicationOwner_ { 

    public static volatile SingularAttribute<ApplicationOwner, Long> id;
    public static volatile SingularAttribute<ApplicationOwner, Application> app;
    public static volatile SingularAttribute<ApplicationOwner, LinuxStoreUser> user;
    public static volatile SingularAttribute<ApplicationOwner, Boolean> isAuthor;

}