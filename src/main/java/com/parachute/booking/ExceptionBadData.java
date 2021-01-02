package com.parachute.booking;

public class ExceptionBadData extends RuntimeException{

    public ExceptionBadData(String message) {
        super(message);
    }
}
