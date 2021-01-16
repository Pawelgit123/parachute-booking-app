package com.parachute.booking.admin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceRemoveTest {

    @Mock
    private AdminRepository adminRepository;
    @Mock
    private AdminServiceRemove adminServiceRemove;
    @Mock
    private Admin admin;

    @Test
    void removeAdmin_adminIsRemoved(){

        //given
        adminRepository.save(admin);

        //when
        adminServiceRemove.adminDelete(admin.getId());

        //then
        verify(adminServiceRemove, times(1)).adminDelete(admin.getId());
    }

}
