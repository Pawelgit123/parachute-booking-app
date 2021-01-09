package com.parachute.booking.mappers;

import org.springframework.stereotype.Component;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;

@Component
public class ClientMapper {
    ClientDto mapClientToClientDto(Client client){
        return ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .pesel(client.getPesel())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .build();
    }
}
