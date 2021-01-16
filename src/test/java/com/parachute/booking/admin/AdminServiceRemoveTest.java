package com.parachute.booking.admin;

import com.parachute.booking.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceRemoveTest {

    private static final Long ID = 1L;

    @Mock
    private AdminRepository adminRepository;
    @InjectMocks
    private AdminServiceRemove adminServiceRemove;

    private Admin createNewAdminForTest() {
        return  Admin.builder()
                .login("Admin2")
                .password("Admin pass")
                .email("admin@gmail.com")
                .id(ID)
                .build();
    }

    @BeforeEach
    void setup() {
        adminRepository.deleteAll();
    }

    @Test
    void removePilotById_whenPilotExists() {
        //given
        when(adminRepository.findById(ID)).thenReturn(Optional.of(createNewAdminForTest()));

        //when
        adminServiceRemove.removeAdminById(ID);

        //then
        verify(adminRepository, times(1)).deleteById(ID);
    }

    @Test
    void removePilotById_whenPilotDoesntExist() {
        //given
        when(adminRepository.findById(ID)).thenReturn(Optional.empty());

        //when

        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> adminServiceRemove.removeAdminById(ID));
        verify(adminRepository, never()).deleteById(ID);
    }

}
