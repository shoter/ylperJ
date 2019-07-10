package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Common.StringHelper;
import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.ServiceBase;
import org.locationtech.jts.geom.Coordinate;

import java.util.Date;
import java.util.List;

public class UserServiceImpl extends ServiceBase<User> implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MethodResult canAddUser(final User user)
    {
        MethodResult result = new MethodResult();
        if(user.getGender() != null && Genders.isCorrectGender(user.getGender().getId()) == false)
        {
            result.addError(UserErrors.incorrectGender);
        }

        if(user.getBirthDay() != null && user.getBirthDay().after(new Date()))
        {
            result.addError(UserErrors.birthdayCannotBeInFuture);
        }

        if(user.getUsername() != null && StringHelper.isStringTrimmed(user.getUsername()) == false)
        {
            result.addError(UserErrors.usernameNotTrimmed);
        }
        else if(userRepository.userExists(user.getUsername()))
        {
            result.addError(UserErrors.userWithUsernameExist);
        }


        result = MethodResult.merge(result, validate(user));

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

        if(user == null || userRepository.getUser(user.getId()) == null)
        {
            return new MethodResult(UserErrors.userNotExist);
        }

        MethodResult result = new MethodResult();
        if(userRepository.hasAnyBookings(user.getId()))
        {
            return new MethodResult(UserErrors.cannotRemoveBecauseOfBookings);
        }
        if(userRepository.hasAnyDemands(user.getId()))
        {
            return new MethodResult(UserErrors.cannotRemoveBecauseOfDemands);
        }

        return result;
    }

    public void removeUser(final User user) {
         userRepository.remove(user);
    }

    public List<User> getUsers() {
        return userRepository.getAll();
    }
}
