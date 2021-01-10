package com.parachute.booking.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findAdminByLogin(String login);

    Admin findAdminByEmail(String email);

//    @Query(value = "SELECT a FROM admin a WHERE ..")
//    Admin findAdminByLoginAndEmailOrLoginOrderByLoginDesc(...)

    // Request processing failed; nested exception is java.lang.IllegalStateException
}
