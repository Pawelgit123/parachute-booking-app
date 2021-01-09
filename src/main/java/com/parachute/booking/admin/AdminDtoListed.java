package com.parachute.booking.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDtoListed {

    Set<Admin> admins;

    //TODO check if it is necessary
}
