package com.parachute.booking.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findAdminByLogin(String login);

    Admin findAdminByEmail(String email);

    // Request processing failed; nested exception is java.lang.IllegalStateException
}
