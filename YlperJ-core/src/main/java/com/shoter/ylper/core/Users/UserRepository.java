package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Repository;

import java.util.List;

public interface UserRepository extends Repository<User> {
    User getUser(long userId);

    boolean hasAnyBookings(long userId);

    boolean hasAnyDemands(long userId);

    boolean userExists(String username);
    boolean userExists(long userId);

    List<User> getAll();
}
