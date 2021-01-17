package com.parachute.booking.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
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
    public ErrorMessage internalServerExceptionHandler(InternalServerException internalServerException) {
        log.error(internalServerException.getMessage());
        return new ErrorMessage(List.of(internalServerException.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestExceptionHandler(BadRequestException badRequestException) {
        log.error(badRequestException.getMessage());
        return new ErrorMessage(List.of(badRequestException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidArgumentExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException){
        final List<String> errors = methodArgumentNotValidException
                .getAllErrors()
                .stream()
                .map(x->x.getDefaultMessage())
                .collect(Collectors.toList());
        return new ErrorMessage(errors);
    }
}
