package com.example.clearsolutiontesttask.controller;

import com.example.clearsolutiontesttask.dto.user.DateRangeDto;
import com.example.clearsolutiontesttask.dto.user.UserRequestDto;
import com.example.clearsolutiontesttask.dto.user.UserResponseDto;
import com.example.clearsolutiontesttask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get all users by birth date in date range",
            description = "Returns a list of users")
    @GetMapping
    public List<UserResponseDto> getAllByDateRange(@RequestBody DateRangeDto dateRangeDto) {
        return userService.findAllByDateRange(dateRangeDto);
    }

    @Operation(summary = "Deletes user by id",
            description = "Deletes user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Long id) {
        userService.deleteById(id);
    }

    @Operation(summary = "Create new user",
            description = "Creates a new user in db")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponseDto create(@RequestBody @Valid UserRequestDto requestDto) {
        return userService.create(requestDto);
    }

    @Operation(summary = "Update all info about user by id",
            description = "Updates all data about user in the db by id")
    @PutMapping("/{id}")
    public UserResponseDto put(@RequestBody @Valid UserRequestDto requestDto,
                                 @PathVariable @Positive Long id) {
        return userService.putById(requestDto, id);
    }

    @Operation(summary = "Update some info about user by id",
            description = "Updates some data about user in the db by id")
    @PatchMapping("/{id}")
    public UserResponseDto patch(@RequestBody @Valid UserRequestDto requestDto,
                                  @PathVariable @Positive Long id) {
        return userService.patchById(requestDto, id);
    }
}
