package com.parachute.booking.admin;

import com.parachute.booking.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceRemove {

    private final AdminRepository adminRepository;

    public void adminDelete(Long id){

        if(adminRepository.findById(id).isPresent()){
            adminRepository.deleteById(id);
        } else{
            throw new NotFoundException("Not found admin to delete with ID: "+id);
        }
    }
}
