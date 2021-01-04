package com.parachute.booking.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFoundExceptionHandler(NotFoundException notFoundException) {
        log.error(notFoundException.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void internalServerExceptionHandler(InternalServerException internalServerException) {
        log.error(internalServerException.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badRequestExceptionHandler(BadRequestException badRequestException) {
        log.error(badRequestException.getMessage());
    }

    @ExceptionHandler(BlankSpaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void blankSpaceExceptionHandler(BlankSpaceException blankSpaceException) {
        log.error(blankSpaceException.getMessage());
    }
}
