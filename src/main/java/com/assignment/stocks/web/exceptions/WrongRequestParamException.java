package com.assignment.stocks.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongRequestParamException extends RuntimeException {

    public WrongRequestParamException(String message) {
        super(message);
    }

}
