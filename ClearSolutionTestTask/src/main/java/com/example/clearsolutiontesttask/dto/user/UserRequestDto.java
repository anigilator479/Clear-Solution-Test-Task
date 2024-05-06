package com.example.clearsolutiontesttask.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserRequestDto {
    @Email
    private String email;

    @Length(min = 2, max = 50)
    private String firstName;

    @Length(min = 2, max = 50)
    private String lastName;

    private LocalDate birthDate;

    @Length(min = 2, max = 50)
    private String address;

    @Pattern(regexp = "^\\+\\d+$", message = "Invalid phone number")
    @Length(min = 6, max = 14)
    private String phoneNumber;
}
