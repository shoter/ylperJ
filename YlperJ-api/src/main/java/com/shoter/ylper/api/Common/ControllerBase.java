package com.shoter.ylper.api.Common;

import com.shoter.ylper.core.Results.MethodResult;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


public class ControllerBase {
    protected Validator validator;

    public ControllerBase(Validator validator)
    {
        this.validator = validator;
    }

    public ResponseEntity response(int httpCode, Object body)
    {
        return ResponseEntity.status(httpCode).body(body);
    }
    public ResponseEntity response400(Object body)
    {
        return response(400,body);
    }

    public <T> ResponseEntity response400(Set<ConstraintViolation<T>> violations)
    {
        return response400(new ErrorResponse(new MethodResult(violations)));
    }
}
