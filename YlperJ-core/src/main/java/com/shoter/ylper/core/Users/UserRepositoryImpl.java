package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.RepositoryBase;
import com.sun.jmx.mbeanserver.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserRepositoryImpl extends RepositoryBase<User> implements  UserRepository {
    public UserRepositoryImpl(Session session) {
        super(session);
    }

    public User getUser(long userId) {
        return session.get(User.class, userId);
    }

    public boolean hasAnyDemandsOrBookings(long userId) {
        return false;
    }

    public boolean userExist(String username) {
        Query query = session.createQuery("select 1 from Car where Car.username=:username");
        query.setParameter("username", username);

        return query.uniqueResult() != null;
    }
}
