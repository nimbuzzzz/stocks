package com.assignment.stocks.web.error;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorDTO {

    private int status;
    private String message;
    private String path;
    private String error;
    private Date timeStamp;
}
