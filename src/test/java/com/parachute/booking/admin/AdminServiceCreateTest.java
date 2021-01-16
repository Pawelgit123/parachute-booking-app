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
class AdminServiceCreateTest {

    @Mock
    private AdminRepository adminRepository;
    @InjectMocks
    private AdminServiceCreate adminServiceCreate;
    @Mock
    private AdminDataValidate adminDataValidate;
    @Mock
    private AdminMapper adminMapper;

    @BeforeEach
    void setup() {
        adminRepository.deleteAll();
    }

    private AdminDto createNewAdminDtoForTest() {
        AdminDto adminDto = new AdminDto.AdminDtoBuilder()
                .login("Admin2")
                .password("Admin pass")
                .email("admin@gmail.com")
                .build();
        return adminDto;
    }

    @Test
    void createAdmin_saveAdminToRepository() {
        //given
        when(adminRepository.save(any(Admin.class))).thenReturn(new Admin());
        when(adminMapper.mapAdminDto(new Admin())).thenReturn(createNewAdminDtoForTest());

        //when
        AdminDto newAdminDto = adminServiceCreate.createNewAdmin(createNewAdminDtoForTest());

        //then
        assertThat(newAdminDto).isExactlyInstanceOf(AdminDto.class);
        verify(adminRepository).save(any(Admin.class));
    }

}
