package com.parachute.booking.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AdminServiceCreateUnitTest {

    @Mock
    AdminRepository adminRepository;
    @InjectMocks
    AdminServiceCreate adminServiceCreate;
    @Mock
    AdminDataValidate adminDataValidate;

    @BeforeEach
    void setup() {
        adminRepository.deleteAll();
    }

    @Test
    void createAdmin_saveAdminToRepository() {
        //given
        when(adminRepository.save(any(Admin.class))).thenReturn(new Admin());

        //when
        AdminDto newAdminDto = adminServiceCreate.createNewAdmin(new AdminDto(1L, "Admin1", "Admin pass", "admin@gmail.com"));

        //then
        assertThat(newAdminDto).isExactlyInstanceOf(AdminDto.class);
        verify(adminRepository).save(any(Admin.class));
    }

}
