package com.shoter.ylper.core.Results;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MethodResult {

    private List<String> errors;

    public MethodResult(String error) {
        this();
        this.errors.add(error);
    }

    public MethodResult()
    {
        this.errors = new ArrayList<String>();

    }

    public MethodResult addError(String error)
    {
        this.errors.add(error);
        return this;
    }

    public <T> MethodResult addError(Collection<ConstraintViolation<T>> violations)
    {
        for(ConstraintViolation v : violations)
        {
            this.errors.add(v.getPropertyPath().toString() + " - " + v.getMessage());
        }

        return this;
    }

    public boolean isSuccess()
    {
        return this.errors.size() == 0;
    }

    public boolean isFailure()
    {
        return !this.isSuccess();
    }
}
