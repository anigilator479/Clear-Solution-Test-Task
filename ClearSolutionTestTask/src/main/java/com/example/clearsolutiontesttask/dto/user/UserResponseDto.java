package com.example.clearsolutiontesttask.dto.user;

public record UserResponseDto(
        String email,

        String firstName,

        String lastName,

        String birthDate,

        String address,

        String phoneNumber
) {
}
