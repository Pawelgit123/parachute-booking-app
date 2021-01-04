package com.parachute.booking.admin;

import com.parachute.booking.exceptions.BadRequestException;
import com.parachute.booking.exceptions.BlankSpaceException;
import com.parachute.booking.exceptions.InternalServerException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AdminDataValidate {

    void validateData(AdminDto adminDto) {

        if (adminDto == null) {
            throw new InternalServerException("No data to create Admin");
        }
        if (adminDto.getPassword().isEmpty()) {
            throw new BadRequestException("Password is empty");
        }
        if (adminDto.getLogin().isEmpty()) {
            throw new BadRequestException("Login is empty");
        }
        if (adminDto.getEmail().isEmpty()) {
            throw new BadRequestException("Email is empty");
        }
        if (!adminDto.getEmail().contains("@")) {
            throw new BadRequestException("Email is inappropriate");
        }
        if (adminDto.getLogin().isBlank()) {
            throw new BlankSpaceException("Login is blank");
        }
        if (adminDto.getPassword().isBlank()) {
            throw new BlankSpaceException("Password is blank");
        }
        if (adminDto.getLogin().length() <= 5) {
            throw new BadRequestException("Login must have more than 5 letters");
        }
        if (adminDto.getPassword().length() <= 5) {
            throw new BadRequestException("Password must have more than 5 letters");
        }

    }
}
