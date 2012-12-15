package com.linuxstore.ejb.entity;

import com.linuxstore.ejb.entity.Application.Category;
import com.linuxstore.ejb.entity.Comment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-11-23T08:49:50")
@StaticMetamodel(Application.class)
public class Application_ { 

    public static volatile SingularAttribute<Application, Long> id;
    public static volatile SingularAttribute<Application, Category> category;
    public static volatile SingularAttribute<Application, Float> price;
    public static volatile SingularAttribute<Application, String> imagePath;
    public static volatile SingularAttribute<Application, String> description;
    public static volatile SingularAttribute<Application, String> filePath;
    public static volatile SingularAttribute<Application, String> name;
    public static volatile ListAttribute<Application, Comment> commentList;

}