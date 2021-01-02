package com.parachute.booking.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    Admin admin = new Admin();

    @BeforeEach
    void setup() {
        admin.setLogin("Admin1");
        admin.setPassword("Secret Code");
        admin.setEmail("admin@gmail.com");
    }

    @Test
    void emailContainsMonkey() {
        assertEquals("admin@gmail.com", admin.getEmail());
        assertTrue(admin.getEmail().contains("@"));
    }

    @Test
    void loginHaveMoreThanFiveLetters() {
        assertTrue(admin.getLogin().length() >= 5);
    }

    @Test
    void passwordHaveMoreThanFiveLetters() {
        assertTrue(admin.getPassword().length() >= 5);
    }

    @Test
    void loginIsNotBlack() {
        assertFalse(admin.getLogin().isBlank());
    }

    @Test
    void passwordIsNotBlack() {
        assertFalse(admin.getPassword().isBlank());
    }

}
