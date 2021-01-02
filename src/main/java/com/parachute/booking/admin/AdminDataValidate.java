package com.parachute.booking.admin;

import com.parachute.booking.ExceptionBadData;
import com.parachute.booking.ExceptionBlankSpaces;
import com.parachute.booking.ExceptionNoData;


public class AdminDataValidate {

    void validateData(AdminDto adminDto) {

        if (adminDto == null) {
            throw new ExceptionNoData("No data to create Admin");
        }
        if (adminDto.getPassword().isEmpty()) {
            throw new ExceptionBadData("Passowrod is empty");
        }
        if (adminDto.getLogin().isEmpty()) {
            throw new ExceptionBadData("Login is empty");
        }
        if (adminDto.getEmail().isEmpty()) {
            throw new ExceptionBadData("Email is empty");
        }
        if (!adminDto.getEmail().contains("@")) {
            throw new ExceptionBadData("Email is inappropriate");
        }
        if (adminDto.getLogin().isBlank()) {
            throw new ExceptionBlankSpaces("Login is blank");
        }
        if (adminDto.getPassword().isBlank()) {
            throw new ExceptionBlankSpaces("Password is blank");
        }
        if (adminDto.getLogin().length() <= 5) {
            throw new ExceptionBadData("Login must have more than 5 letters");
        }
        if (adminDto.getPassword().length() <= 5) {
            throw new ExceptionBadData("Password must have more than 5 letters");
        }

    }
}
