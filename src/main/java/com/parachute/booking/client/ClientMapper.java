package com.parachute.booking.client;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;

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
