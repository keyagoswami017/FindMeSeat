package com.neu.csye6220.libseatmgmt.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Level;
import java.util.logging.Logger;


@Repository
public class BaseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private static final ThreadLocal<Session> sessionThread = new ThreadLocal<>();
    private static final Logger logger = Logger.getAnonymousLogger();

    protected BaseDAO() {
    }

    protected Session getSession() {
        Session session = (Session) sessionThread.get();
        if (session == null) {
            session = sessionFactory.openSession();
            sessionThread.set(session);
        }
        //System.out.println("SessionFactory created in BaseDAO: " + sessionFactory);

        return session;
    }

    protected void begin() {
        Session session = getSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
    }

    protected void commit() {
        Transaction tx = getSession().getTransaction();
        if (tx != null && tx.isActive()) {
            tx.commit();
        }
    }

    protected void rollback() {
        try {
            Transaction tx = getSession().getTransaction();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } catch (HibernateException e) {
            logger.log(Level.WARNING, "Could not rollback transaction", e);
        }
    }

    public void close() {
        Session session = getSession();
        if (session != null && session.isOpen()) {
            session.close();
            sessionThread.remove();
        }
    }
}
