package com.assignment.stocks.web.error;

import com.assignment.stocks.web.exceptions.InvalidRequestParamException;
import com.assignment.stocks.web.exceptions.StockNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidRequestParamException.class)
    public ResponseEntity<ErrorDTO> handleInvalidRequestParamException(InvalidRequestParamException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.name())
                        .timeStamp(new Date())
                        .build());
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleStockNotFoundException(StockNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDTO.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.name())
                        .timeStamp(new Date())
                        .build());
    }



}
