package com.parachute.booking.admin;

import com.parachute.booking.exceptions.BadRequestException;
import com.parachute.booking.exceptions.BlankSpaceException;
import com.parachute.booking.exceptions.InternalServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceCreateTest {

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
        Admin newAdmin = adminServiceCreate.createNewAdmin(new AdminDto(1L, "Admin1", "Admin pass", "admin@gmail.com"));

        //then
        assertThat(newAdmin).isExactlyInstanceOf(Admin.class);
        verify(adminRepository).save(any(Admin.class));
    }

    @Test
    void createAdmin_DtoIsNull() {
        AdminDto adminDto = new AdminDto();

        Throwable result = catchThrowable(() -> adminServiceCreate.createNewAdmin(adminDto));

        assertThat(result).isExactlyInstanceOf(InternalServerException.class);

    }

    @Test
    void createAdmin_loginIsBlank(){
        AdminDto adminDto = new AdminDto.AdminDtoBuilder()
                .login("Admin1")
                .password("   ")
                .email("admin@gmail.com")
                .build();

        Throwable result = catchThrowable(() ->  adminServiceCreate.createNewAdmin(adminDto));

        assertThat(result).isExactlyInstanceOf(BlankSpaceException.class);
    }

    @Test
    void createAdmin_passwordIsBlank(){
        AdminDto adminDto = new AdminDto.AdminDtoBuilder()
                .login("   ")
                .password("Admin pass")
                .email("admin@gmail.com")
                .build();

        Throwable result = catchThrowable(() ->  adminServiceCreate.createNewAdmin(adminDto));

        assertThat(result).isExactlyInstanceOf(BlankSpaceException.class);
    }

    @Test
    void createAdmin_emailIsBlank(){
        AdminDto adminDto = new AdminDto.AdminDtoBuilder()
                .login("Admin1")
                .password("Admin pass")
                .email("  ")
                .build();

        Throwable result = catchThrowable(() ->  adminServiceCreate.createNewAdmin(adminDto));

        assertThat(result).isExactlyInstanceOf(BlankSpaceException.class);
    }

    @Test
    void createAdmin_emailContainsNoMonkey(){
        AdminDto adminDto = new AdminDto.AdminDtoBuilder()
                .login("Admin1")
                .password("Admin pass")
                .email("admin(at)gmail.com")
                .build();

        Throwable result = catchThrowable(() ->  adminServiceCreate.createNewAdmin(adminDto));

        assertThat(result).isExactlyInstanceOf(BadRequestException.class);
    }

}
