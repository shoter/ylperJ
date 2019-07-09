package com.shoter.ylper.core;

import org.hibernate.Session;

public class RepositoryBase<TEntity> implements Repository<TEntity>{
    protected Session session;

    protected RepositoryBase(Session session)
    {
        this.session = session;
    }

    public void add(TEntity entity)
    {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    public void remove(TEntity entity)
    {
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
    }
}
