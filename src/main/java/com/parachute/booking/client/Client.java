package com.parachute.booking.client;

import com.parachute.booking.booking.BookingForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Name is mandatory")
    private String firstName;
    @Column
    @NotBlank(message = "Surname is mandatory")
    private String lastName;
    @Column
    @NotBlank(message = "PESEL is mandatory")
    private String pesel;
    @Column
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;
    @Column
    @NotBlank(message = "E-mail is mandatory")
    private String email;
    @OneToMany(mappedBy = "client")
    private List<BookingForm> bookingForms;

}
