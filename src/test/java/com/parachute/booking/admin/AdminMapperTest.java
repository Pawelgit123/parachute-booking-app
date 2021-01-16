package com.parachute.booking.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AdminMapperTest {

    @Mock
    private AdminRepository adminRepository;

    private AdminDto createNewAdminDtoForTest() {
        return AdminDto.builder()
                .login("Admin2")
                .password("Admin pass")
                .email("admin@gmail.com")
                .build();
    }

    private Admin createNewAdminForTest() {
        return Admin.builder()
                .login("Admin2")
                .password("Admin pass")
                .email("admin@gmail.com")
                .build();
    }

    @BeforeEach
    void setup() {
        adminRepository.deleteAll();
    }

    @Test
    void mapAdminDto() {
        //given
        AdminMapper adminMapper = new AdminMapper();

        //when
        AdminDto adminDto = adminMapper.mapAdminDto(createNewAdminForTest());

        //then
        assertThat(adminDto).isExactlyInstanceOf(AdminDto.class);
        assertThat(adminDto.getLogin()).isEqualTo(createNewAdminDtoForTest().getLogin());
        assertThat(adminDto.getPassword()).isEqualTo(createNewAdminDtoForTest().getPassword());
        assertThat(adminDto.getEmail()).isEqualTo(createNewAdminDtoForTest().getEmail());
    }

    @Test
    void mapAdmin() {
        //given
        AdminMapper adminMapper = new AdminMapper();

        //when
        Admin admin = adminMapper.mapAdmin(createNewAdminDtoForTest());

        //then
        assertThat(admin).isExactlyInstanceOf(Admin.class);
        assertThat(admin.getLogin()).isEqualTo(createNewAdminForTest().getLogin());
        assertThat(admin.getPassword()).isEqualTo(createNewAdminForTest().getPassword());
        assertThat(admin.getEmail()).isEqualTo(createNewAdminForTest().getEmail());
    }
}
