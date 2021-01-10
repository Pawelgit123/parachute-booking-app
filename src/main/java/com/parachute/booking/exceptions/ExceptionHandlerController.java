package com.parachute.booking.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundExceptionHandler(NotFoundException notFoundException) {
        log.error(notFoundException.getMessage());
        return new ErrorMessage(List.of(notFoundException.getMessage()));
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handle(MethodArgumentNotValidException exp) {
        final List<String> errors = exp.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        return new ErrorMessage(errors);
    }
}
