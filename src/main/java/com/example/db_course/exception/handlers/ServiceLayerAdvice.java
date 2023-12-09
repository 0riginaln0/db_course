package com.example.db_course.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ServiceLayerAdvice {
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String myEntityNotFoundHandler(EntityNotFoundException ex) {
        log.error("\n" + ex.getMessage()
                + "\n    file name: " + ex.getStackTrace()[0].getFileName()
                + "\n   class name: " + ex.getStackTrace()[0].getClassName()
                + "\n  method name: " + ex.getStackTrace()[0].getMethodName()
                + "\n  line number: " + ex.getStackTrace()[0].getLineNumber());
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String myEntityNotFoundHandler(JpaSystemException ex) {
        log.error("\n" + ex.getMessage()
                + "\n    file name: " + ex.getStackTrace()[0].getFileName()
                + "\n   class name: " + ex.getStackTrace()[0].getClassName()
                + "\n  method name: " + ex.getStackTrace()[0].getMethodName()
                + "\n  line number: " + ex.getStackTrace()[0].getLineNumber());
        return ex.getMessage();
    }
}
