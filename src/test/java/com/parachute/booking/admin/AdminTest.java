package com.parachute.booking.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    Admin admin = new Admin();

    @BeforeEach
    void setup() {
        admin.setLogin("Admin1");
        admin.setPassword("Admin pass");
        admin.setEmail("admin@gmail.com");
    }

    @Test
    void emailContainsMonkey() {
        assertTrue(admin.getEmail().contains("@"));
    }

    @Test
    void loginHaveMoreThanFiveLettersOrEquals() {
        assertTrue(admin.getLogin().length() >= 5);
    }

    @Test
    void passwordHaveMoreThanFiveLettersOrEquals() {
        assertTrue(admin.getPassword().length() >= 5);
    }

    @Test
    void loginIsNotBlank() {
        assertFalse(admin.getLogin().isBlank());
    }

    @Test
    void passwordIsNotBlank() {
        assertFalse(admin.getPassword().isBlank());
    }

    @Test
    void emailIsNotBlank() {
        assertFalse(admin.getEmail().isBlank());
    }

}
