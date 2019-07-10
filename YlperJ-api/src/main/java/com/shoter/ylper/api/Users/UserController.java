package com.shoter.ylper.api.Users;

import com.shoter.ylper.api.Users.Models.UserModel;
import com.shoter.ylper.core.Users.User;
import com.shoter.ylper.core.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping( value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
       this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<UserModel> getUsers()
    {
        List<User> users = userService.getUsers();

        List<UserModel> returnVal = new ArrayList<UserModel>(users.size());

        for(User u : users)
            returnVal.add(new UserModel(u));

        return returnVal;
    }
}
