package com.parachute.booking.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByLogin(String login);

    Optional<Admin> findAdminByEmail(String email);

}
