package com.parachute.booking.admin;

import com.parachute.booking.exceptions.InternalServerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceCreateRepositoryTest {

    @Mock
    private AdminRepository adminRepository;
    @InjectMocks
    private AdminServiceCreate adminServiceCreate;
    @Mock
    private AdminMapper adminMapper;

    @BeforeEach
    void setup() {
        adminRepository.deleteAll();
    }

    @Test
    void createAdmin_saveAdminToRepository() {
        //given
        when(adminRepository.save(any(Admin.class))).thenReturn(new Admin());
        when(adminMapper.mapAdmin(new AdminDto())).thenReturn(new Admin());
        when(adminMapper.mapAdminDto(new Admin())).thenReturn(new AdminDto());

        //when
        AdminDto newAdminDto = adminServiceCreate.createNewAdmin(new AdminDto());

        //then
        assertThat(newAdminDto).isExactlyInstanceOf(AdminDto.class);
        verify(adminRepository).save(any(Admin.class));
    }
    @Test
    void createNewAdmin_InternalServerError_byNull() {
        //when
        Throwable throwable = catchThrowable(() -> adminServiceCreate.createNewAdmin(null));

        //then
        assertThat(throwable).isInstanceOf(InternalServerException.class);
        verify(adminRepository, times(0)).save(any(Admin.class));
    }

}
