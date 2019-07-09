package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Database.SessionOperation;
import com.shoter.ylper.core.Database.SessionTransactionOperation;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;
import org.hibernate.Session;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UserServiceImpl extends ServiceBase implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MethodResult canAddUser(final User user)
    {
        MethodResult result = new MethodResult();
        if(user.getGender() != null && Genders.isCorrectGender(user.getGender().getId()) == false)
        {
            result.addError("Incorrect gender");
        }

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        result.addError(violations);

        return result;
    }

    public void addUser(final User user) {
        userRepository.add(user);
    }

    public User getUser(final long userId)
    {
        return userRepository.getUser(userId);
    }

    public MethodResult canRemoveUser(User user) {

        //TODO: Check if has any bookings/demands - if yes then we cannot remove him.

        return new MethodResult();
    }

    public void removeUser(final User user) {
         userRepository.remove(user);
    }
}
