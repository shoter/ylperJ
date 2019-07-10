package com.shoter.ylper.api.Common;

import com.shoter.ylper.core.Results.MethodResult;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private String message;

    private List<String> errors;

    public ErrorResponse(MethodResult result)
    {
        message = "There was error while processing your request. You can find more detailed informations inside errors field.";
        this.errors = new ArrayList<String>(result.getErrors());

    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
