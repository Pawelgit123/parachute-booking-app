package com.parachute.booking.admin;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    private Long id;
    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;
    /*
    {
      login: "admin",
      password: "Admin123"
      email: "test#gmail.com"
    }

    400
    {
      msg: "Email has incorrect format"
    }

     */
    //test#gmail.com
}
