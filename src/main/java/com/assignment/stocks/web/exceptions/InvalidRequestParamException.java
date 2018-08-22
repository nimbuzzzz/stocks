package com.assignment.stocks.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidRequestParamException extends RuntimeException {

    public InvalidRequestParamException(String message) {
        super(message);
    }

}
