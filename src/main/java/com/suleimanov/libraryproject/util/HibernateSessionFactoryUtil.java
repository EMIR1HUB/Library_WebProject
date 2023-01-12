package com.suleimanov.libraryproject.util;

import com.suleimanov.libraryproject.models.Person;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Person.class);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}