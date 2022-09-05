package com.blz.hiringcandidate.exception;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomNotFoundexception extends RuntimeException{
    private int statusCode;
    private String statusMessage;
    public CustomNotFoundexception(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}