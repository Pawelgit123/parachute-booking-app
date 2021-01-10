package com.parachute.booking.mappers;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;

public class ClientMapper {
    ClientDto mapClientDto(Client client) {
        return ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .pesel(client.getPesel())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .build();
    }
}
