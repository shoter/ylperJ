package com.shoter.ylper.core.Results;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
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

    public <T> MethodResult(Collection<ConstraintViolation<T>> violations)
    {
        this();
        this.addError(violations);
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

    public Collection<String> getErrors()
    {
        return this.errors;
    }

    public boolean hasError(String error)
    {
        for(String e : errors)
        {
            if(e.equals(error))
                return true;
        }

        return false;
    }

    public static MethodResult merge(MethodResult lhm, MethodResult rhm)
    {
        MethodResult result = new MethodResult();

        for(String e : lhm.errors)
            result.addError(e);
        for(String e : rhm.errors)
            result.addError(e);

        return result;
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
