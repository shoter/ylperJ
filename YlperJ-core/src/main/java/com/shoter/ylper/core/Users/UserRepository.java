package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Repository;

public interface UserRepository extends Repository<User> {
    User getUser(long userId);

    boolean hasAnyDemandsOrBookings(long userId);
}
