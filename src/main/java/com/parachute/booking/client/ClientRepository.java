package com.parachute.booking.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ClientRepository extends JpaRepository <Client, Long> {

    @Query("SELECT email FROM client")
    Collection<Client> findAllEmails();

    @Query("SELECT pesel FROM client")
    Collection<Client> findAllPesels();

    @Query("SELECT phoneNumber FROM client")
    Collection<Client> findAllPhoneNumbers();
}
