package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Bookings.Booking;
import com.shoter.ylper.core.Demands.Demand;
import com.shoter.ylper.core.Results.MethodResult;

import java.util.List;

public interface UserService {
    MethodResult canAddUser(User user);
    void addUser(User user);
    User getUser(long userId);
    boolean exists(long userId);

    MethodResult canRemoveUser(User user);
    void removeUser(User user);

    List<User> getUsers();
    List<Demand> getDemands(long userId);
    List<Booking> getBookings(long userId);
}
