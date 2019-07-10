package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.RepositoryBase;
import com.sun.jmx.mbeanserver.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl extends RepositoryBase<User> implements  UserRepository {
    public UserRepositoryImpl(Session session) {
        super(session);
    }

    public User getUser(long userId) {
        return session.get(User.class, userId);
    }

    public boolean hasAnyBookings(long userId) {
       Query query = session.createQuery("select 1 from Booking booking where booking.userId=:userId") ;
       query.setParameter("userId", userId);

       return query.uniqueResult() != null;
    }

    public boolean hasAnyDemands(long userId) {
        Query query = session.createQuery("select 1 from Demand demand where demand.userId=:userId") ;
        query.setParameter("userId", userId);

        return query.uniqueResult() != null;
    }


    public boolean userExists(String username) {
        Query query = session.createQuery("select 1 from User user where user.username=:username");
        query.setParameter("username", username);

        return query.uniqueResult() != null;
    }

    public boolean userExists(long userId) {
        Query query = session.createQuery("select 1 from User user where user.id=:userId");
        query.setParameter("userId", userId);

        return query.uniqueResult() != null;
    }

    public List<User> getAll() {
        return session.createQuery("from User").list();
    }
}
