package com.example.clearsolutiontesttask.service;

import com.example.clearsolutiontesttask.dto.user.DateRangeDto;
import com.example.clearsolutiontesttask.dto.user.UserRequestDto;
import com.example.clearsolutiontesttask.dto.user.UserResponseDto;
import com.example.clearsolutiontesttask.exception.InvalidFieldValueException;
import com.example.clearsolutiontesttask.mapper.UserMapper;
import com.example.clearsolutiontesttask.mapper.impl.UserMapperImpl;
import com.example.clearsolutiontesttask.model.User;
import com.example.clearsolutiontesttask.repository.UserRepository;
import com.example.clearsolutiontesttask.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private UserMapper userMapper = new UserMapperImpl();

    @Test
    @DisplayName("Verify that correct user creates")
    public void createUser_validFields_Success() {
        // Given
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setAddress("sobaka");
        userRequestDto.setEmail("sobaka@mail.com");
        userRequestDto.setBirthDate(LocalDate.of(2000, 6, 6));
        userRequestDto.setFirstName("Alex");
        userRequestDto.setLastName("Lev");
        userRequestDto.setPhoneNumber("+3805432522");

        User user = new User();
        user.setAddress("sobaka");
        user.setEmail("sobaka@mail.com");
        user.setBirthDate(LocalDate.of(2000, 6, 6));
        user.setFirstName("Alex");
        user.setLastName("Lev");
        user.setPhoneNumber("+3805432522");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findUserByPhoneNumber(userRequestDto.getPhoneNumber())).thenReturn(Optional.empty());

        // When
        UserResponseDto actual = userService.create(userRequestDto);

        // Then
        UserResponseDto expected = new UserResponseDto(
                "sobaka@mail.com",
                "Alex",
                "Lev",
                "2000-06-06",
                "sobaka",
                "+3805432522"
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Verify that creating user with existing email throws exception")
    public void createUser_existingEmail_ThrowsException() {
        // Given
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("existing@mail.com");

        Mockito.when(userRepository.findUserByEmail("existing@mail.com")).thenReturn(Optional.of(new User()));

        // When, Then
        Assertions.assertThrows(InvalidFieldValueException.class, () -> userService.create(userRequestDto));
    }

    @Test
    @DisplayName("Verify that creating user with existing phone number throws exception")
    public void createUser_existingPhoneNumber_ThrowsException() {
        // Given
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setPhoneNumber("+123456789");

        Mockito.when(userRepository.findUserByPhoneNumber("+123456789")).thenReturn(Optional.of(new User()));

        // When, Then
        Assertions.assertThrows(InvalidFieldValueException.class, () -> userService.create(userRequestDto));
    }

    @Test
    @DisplayName("Verify that find all users by date range returns correct list")
    public void findAllByDateRange_validDateRange_Success() {
        // Given
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        DateRangeDto dateRangeDto = new DateRangeDto(startDate, endDate);

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setAddress("Address 1");
        user1.setEmail("john@mail.com");
        user1.setPhoneNumber("+123456789");
        user1.setBirthDate(LocalDate.of(1990, 5, 15));
        user1.setFirstName("John");
        user1.setLastName("Doe");
        User user2 = new User();
        user2.setAddress("Address 2");
        user2.setEmail("jane@mail.com");
        user2.setPhoneNumber("+987654321");
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setBirthDate(LocalDate.of(1985, 10, 20));
        userList.add(user1);
        userList.add(user2);

        Mockito.when(userRepository.findUserByBirthDateBetween(startDate, endDate)).thenReturn(userList);

        // When
        List<UserResponseDto> actual = userService.findAllByDateRange(dateRangeDto);

        // Then
        List<UserResponseDto> expected = Arrays.asList(
                new UserResponseDto("john@mail.com", "John", "Doe", "1990-05-15", "Address 1", "+123456789"),
                new UserResponseDto("jane@mail.com", "Jane", "Smith", "1985-10-20", "Address 2", "+987654321")
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Verify that user can be patched successfully")
    public void patchById_validRequest_Success() {
        // Given
        Long userId = 1L;
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("newemail@mail.com");
        userRequestDto.setBirthDate(LocalDate.of(1990, 5, 15));

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("oldemail@mail.com");
        existingUser.setBirthDate(LocalDate.of(1990, 1, 1));

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.empty());

        // When
        UserResponseDto patchedUser = userService.patchById(userRequestDto, userId);

        // Then
        Assertions.assertEquals(userRequestDto.getEmail(), patchedUser.email());
        Assertions.assertEquals(userRequestDto.getBirthDate().toString(), patchedUser.birthDate());
    }

    @Test
    @DisplayName("Verify that patching user with same email throws exception")
    public void patchById_sameEmail_ThrowsException() {
        // Given
        Long userId = 1L;
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("existing@mail.com");
        userRequestDto.setBirthDate(LocalDate.of(1990, 5, 15));

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("existing@mail.com");
        existingUser.setBirthDate(LocalDate.of(1990, 1, 1));

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // When, Then
        Assertions.assertThrows(InvalidFieldValueException.class, () -> userService.patchById(userRequestDto, userId));
    }

    @Test
    @DisplayName("Verify that user can be updated successfully")
    public void putById_validRequest_Success() {
        // Given
        Long userId = 1L;
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("newemail@mail.com");
        userRequestDto.setBirthDate(LocalDate.of(1990, 5, 15));

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("oldemail@mail.com");
        existingUser.setBirthDate(LocalDate.of(1990, 1, 1));

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.findUserByEmail(userRequestDto.getEmail())).thenReturn(Optional.empty());

        // When
        UserResponseDto updatedUser = userService.putById(userRequestDto, userId);

        // Then
        Assertions.assertEquals(userRequestDto.getEmail(), updatedUser.email());
        Assertions.assertEquals(userRequestDto.getBirthDate().toString(), updatedUser.birthDate());
    }

    @Test
    @DisplayName("Verify that updating user with same email throws exception")
    public void putById_sameEmail_ThrowsException() {
        // Given
        Long userId = 1L;
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("existing@mail.com");
        userRequestDto.setBirthDate(LocalDate.of(1990, 5, 15));

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("existing@mail.com");
        existingUser.setBirthDate(LocalDate.of(1990, 1, 1));

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // When, Then
        Assertions.assertThrows(InvalidFieldValueException.class, () -> userService.putById(userRequestDto, userId));
    }

    @Test
    @DisplayName("Verify that user can be deleted successfully")
    public void deleteById_validUserId_Success() {
        // Given
        Long userId = 1L;

        // When
        userService.deleteById(userId);

        // Then
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }
}
