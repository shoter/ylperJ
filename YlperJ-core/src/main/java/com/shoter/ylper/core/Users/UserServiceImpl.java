package com.shoter.ylper.core.Users;

import com.shoter.ylper.core.Database.SessionOperation;
import com.shoter.ylper.core.Database.SessionTransactionOperation;
import com.shoter.ylper.core.Results.MethodResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private Session session;
    private Validator validator;

    public UserServiceImpl(Session session) {
        this.session =session;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
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
        new SessionTransactionOperation(session)
        {
            @Override
            protected void Execute() {
                this.session.save(user);
            }
        }.Run();
    }

    public User getUser(final long userId)
    {
        final User[] user = new User[1]; // is this anti pattern?
        // Java designers should make some kind of code sugar to make it more pretty if it is not anti-pattern
        // there are lambdas - I did not yet used lambdas in the past so to not make silly errors I will stay with annonymous classes.
        new SessionOperation(session)
        {
            @Override
            protected void Execute() {
                user[0] = (User) this.session.get(User.class, userId);
            }
        }.Run();

        return user[0];
    }
}
