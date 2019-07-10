package com.shoter.ylper.core;

import com.shoter.ylper.core.Results.MethodResult;
import com.shoter.ylper.core.Users.User;
import org.hibernate.Session;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ServiceBase<TEntity> {
    protected Validator validator;

    protected ServiceBase()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public MethodResult validate(TEntity entity)
    {
        Set<ConstraintViolation<TEntity>> violations = validator.validate(entity);
        return new MethodResult(violations);
    }
}
