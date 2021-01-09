package com.parachute.booking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
@RestControllerAdvice // @Controller + @ResponseBody
@Slf4j
public class ExceptionHandlerController {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ErrorMessage {
        private String msg;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundExceptionHandler(NotFoundException notFoundException) {
        log.error(notFoundException.getMessage());
        return new ErrorMessage(notFoundException.getMessage());
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
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    void blankSpaceExceptionHandler(BlankSpaceException blankSpaceException) {
        log.error(blankSpaceException.getMessage());
    }
}
