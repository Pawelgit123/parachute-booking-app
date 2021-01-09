package com.parachute.booking.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<String> errors) {
        super(errors.stream().collect(Collectors.joining(", ")));
    }
}
