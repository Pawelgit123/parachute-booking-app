package com.parachute.booking.admin;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    private Long id;
    @NotNull
    @Length(min = 5)
    private String login;
    @NotNull
    @Length(min = 5)
    private String password;
    @NotNull
    @Email
    private String email;
}
