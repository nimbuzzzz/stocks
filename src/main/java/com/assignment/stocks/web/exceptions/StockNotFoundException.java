package com.assignment.stocks.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StockNotFoundException extends RuntimeException{

    public StockNotFoundException(String id){
        super("Could not find Stock : " +id);
    }
}
