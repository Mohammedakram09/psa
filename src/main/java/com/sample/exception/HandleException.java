package com.sample.exception;


import com.sample.payload.ErrorDetails;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class HandleException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleStudentNotFound(Exception e,WebRequest request){
        ErrorDetails ed=new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false)

        );
        return new ResponseEntity<>(ed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
