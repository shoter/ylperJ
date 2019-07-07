package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Results.MethodResult;

public interface UserService {
    MethodResult canAddUser(User user);
    void addUser(User user);
    User getUser(long userId);
}
