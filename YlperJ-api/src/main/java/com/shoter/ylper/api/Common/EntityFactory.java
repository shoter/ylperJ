package com.shoter.ylper.api.Common;

import com.shoter.ylper.api.Users.Models.UserModel;
import com.shoter.ylper.core.Users.Gender;
import com.shoter.ylper.core.Users.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EntityFactory {
    private Session session;

    @Autowired
    public EntityFactory(Session session)
    {
        this.session = session;
    }

    // it will be easier to understand what entity class we want to create by specifying class of the class that we want to create.
    public User create(Class<User> userClass, UserModel model)
    {
        Gender gender = session.load(Gender.class, model.getGender());

        User user = new User();
        user.setBirthDay(model.getBirthday());
        user.setName(model.getName());
        user.setUsername(model.getUsername());
        user.setId(model.getId());
        user.setGender(gender);
        user.setCreateDate(new Date());

        return user;
    }

}
