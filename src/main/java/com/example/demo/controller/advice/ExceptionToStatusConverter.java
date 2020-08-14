package com.example.demo.controller.advice;

import com.example.demo.util.exception.file.*;
import com.example.demo.util.exception.user.userException.DuplicateUserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
public class ExceptionToStatusConverter {


    @ResponseBody
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicate(DuplicateUserException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String runTime(RuntimeException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String not(FileNotFoundException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }



    @ResponseBody
    @ExceptionHandler(NoDirectoryException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String no(NoDirectoryException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoResourceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String not(NoResourceException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LeakageException.class)
    @ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
    String leakage(LeakageException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }


   /* @ResponseBody
    @ExceptionHandler(MultipartFilesOutOfOrderException.class)
    @ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
    String mul(MultipartFilesOutOfOrderException e) {
        log.info(">>>" + e.getMessage() + ":");
        return e.getMessage();
    }*/




}
