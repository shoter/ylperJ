package com.shoter.ylper.core;

import org.hibernate.Session;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ServiceBase {
    protected Validator validator;

    protected ServiceBase()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


}
