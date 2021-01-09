package com.parachute.booking.admin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AdminRepositoryTest {

  @Autowired
  private AdminRepository adminRepository;

  @Test
  void test() {
    adminRepository.save(new Admin(null, "login", "pwd133", "test@gmail.com"));
    System.out.println("x");
  }
}