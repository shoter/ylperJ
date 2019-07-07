package com.shoter.ylper.core.Users;

import org.hibernate.SessionFactory;

public class UserService {
    private SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
