package com.example.clearsolutiontesttask.service;

import com.example.clearsolutiontesttask.dto.user.DateRangeDto;
import com.example.clearsolutiontesttask.dto.user.UserRequestDto;
import com.example.clearsolutiontesttask.dto.user.UserResponseDto;
import java.util.List;

public interface UserService {
    UserResponseDto create(UserRequestDto requestDto);

    List<UserResponseDto> findAllByDateRange(DateRangeDto dateRangeDto);

    UserResponseDto patchById(UserRequestDto customerRequestDto, Long id);

    UserResponseDto putById(UserRequestDto customerRequestDto, Long id);

    void deleteById(Long id);
}
