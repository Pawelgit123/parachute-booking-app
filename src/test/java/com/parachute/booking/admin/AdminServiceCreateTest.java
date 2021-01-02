package com.parachute.booking.admin;

import com.parachute.booking.ExceptionNoData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
    void createAdmin_withNullDto() {

    }


//    @Test
//    void adminCreate_isNull() {
//        AdminDto adminDto = new AdminDto();
//
//        Throwable result = catchThrowable(() -> adminServiceCreate.createNewAdmin(adminDto));
//
//        assertThat(result).isExactlyInstanceOf(ExceptionNoData.class);
//
//    }


}
