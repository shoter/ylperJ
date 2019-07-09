package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.RepositoryBase;
import com.sun.jmx.mbeanserver.Repository;
import org.hibernate.Session;

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
}
