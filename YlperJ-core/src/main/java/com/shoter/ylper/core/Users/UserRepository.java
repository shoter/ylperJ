package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Repository;

public interface UserRepository extends Repository<User> {
    User getUser(long userId);

    boolean hasAnyBookings(long userId);

    boolean hasAnyDemands(long userId);

    boolean userExist(String username);
}
