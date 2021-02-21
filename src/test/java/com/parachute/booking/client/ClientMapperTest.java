package com.parachute.booking.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ClientMapperTest {

    @Mock
    private ClientRepository clientRepository;

    private Client createClientForTest() {
        return Client.builder()
                .id(1L)
                .firstName("Bob")
                .lastName("Skywalker")
                .pesel("90023008655")
                .email("Bobski@gmail.com")
                .phoneNumber("+48 943 007 420")
                .build();
    }

    private ClientDto createClientDtoForTest() {
        return ClientDto.builder()
                .firstName("Bob")
                .lastName("Skywalker")
                .pesel("90023008655")
                .email("Bobski@gmail.com")
                .phoneNumber("+48 943 007 420")
                .build();
    }

    @BeforeEach
    void setClientRepositoryClean() {
        clientRepository.deleteAll();
    }

    @Test
    void mapClientToClientDtoTest() {
        //given
        ClientMapper clientMapper = new ClientMapper();

        //when
        ClientDto clientDto = clientMapper.mapClient(createClientForTest());

        //then
        assertThat(clientDto).isExactlyInstanceOf(ClientDto.class);
        assertThat(clientDto.getFirstName()).isEqualTo("Bob");
        assertThat(clientDto.getLastName()).isEqualTo("Skywalker");
        assertThat(clientDto.getPesel()).isEqualTo("90023008655");
        assertThat(clientDto.getEmail()).isEqualTo("Bobski@gmail.com");
        assertThat(clientDto.getPhoneNumber()).isEqualTo("+48 943 007 420");
    }

    @Test
    void mapClientDtoToClientTest() {
        //given
        ClientMapper clientMapper = new ClientMapper();

        //when
        Client client = clientMapper.mapClientDto(createClientDtoForTest());

        //then
        assertThat(client).isExactlyInstanceOf(Client.class);
        assertThat(client.getFirstName()).isEqualTo("Bob");
        assertThat(client.getLastName()).isEqualTo("Skywalker");
        assertThat(client.getPesel()).isEqualTo("90023008655");
        assertThat(client.getEmail()).isEqualTo("Bobski@gmail.com");
        assertThat(client.getPhoneNumber()).isEqualTo("+48 943 007 420");
    }
}
