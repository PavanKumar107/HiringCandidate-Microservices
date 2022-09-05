package com.blz.hiringcandidate.exception.exceptionhandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.blz.hiringcandidate.exception.CustomNotFoundexception;
import com.blz.hiringcandidate.util.Response;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomNotFoundexception.class)
    public ResponseEntity<Response> handleHiringException(CustomNotFoundexception he){
        Response response=new Response();
        response.setErrorCode(400);
        response.setMessage(he.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}