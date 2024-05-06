package com.example.clearsolutiontesttask.mapper;

import com.example.clearsolutiontesttask.dto.user.UserRequestDto;
import com.example.clearsolutiontesttask.dto.user.UserResponseDto;
import com.example.clearsolutiontesttask.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface UserMapper {
    UserResponseDto toUserResponse(User user);

    User toUser(UserRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBook(UserRequestDto userRequestDto, @MappingTarget User user);
}
