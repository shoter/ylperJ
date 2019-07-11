package com.shoter.ylper.core;

import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.Users.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class RepositoryBase<TEntity> implements Repository<TEntity>{
    protected Session session;

    protected RepositoryBase(Session session)
    {
        this.session = session;
    }

    public void add(TEntity entity)
    {
        session.beginTransaction();
        session.persist(entity);
        session.flush();
        session.getTransaction().commit();
    }

    public void remove(TEntity entity)
    {
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
    }

    public void update(TEntity entity) {
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }
}
