package com.assignment.stocks.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

//@ControllerAdvice
public class ErrorHandler {

    //@ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ErrorDTO> handleInvalidArgumentException(){//InvalidArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder()
                        .message("Bad request arguments")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.name())
                        .timeStamp(new Date())
                        .build());
    }
}
