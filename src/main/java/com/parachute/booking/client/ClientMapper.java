package com.parachute.booking.client;

import com.parachute.booking.client.Client;
import com.parachute.booking.client.ClientDto;

public class ClientMapper {

    public ClientDto mapClient(Client client){
        return ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .pesel(client.getPesel())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .build();
    }

    public Client mapClientDto(ClientDto clientDto){
        return Client.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .pesel(clientDto.getPesel())
                .phoneNumber(clientDto.getPhoneNumber())
                .email(clientDto.getEmail())
                .build();
    }
}
