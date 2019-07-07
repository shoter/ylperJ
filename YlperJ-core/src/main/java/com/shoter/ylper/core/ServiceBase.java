package com.shoter.ylper.core;

import org.hibernate.Session;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ServiceBase {
    protected Session session;
    protected Validator validator;

    protected ServiceBase(Session session)
    {
        this.session = session;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


}
