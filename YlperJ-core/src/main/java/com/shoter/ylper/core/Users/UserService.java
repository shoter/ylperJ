package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Results.MethodResult;

import java.util.List;

public interface UserService {
    MethodResult canAddUser(User user);
    void addUser(User user);
    User getUser(long userId);

    MethodResult canRemoveUser(User user);
    void removeUser(User user);

    List<User> getUsers();
}
