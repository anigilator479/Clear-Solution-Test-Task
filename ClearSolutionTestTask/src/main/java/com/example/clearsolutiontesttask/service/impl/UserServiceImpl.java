package com.example.clearsolutiontesttask.service.impl;

import com.example.clearsolutiontesttask.dto.user.DateRangeDto;
import com.example.clearsolutiontesttask.dto.user.UserRequestDto;
import com.example.clearsolutiontesttask.dto.user.UserResponseDto;
import com.example.clearsolutiontesttask.exception.InvalidFieldValueException;
import com.example.clearsolutiontesttask.mapper.UserMapper;
import com.example.clearsolutiontesttask.model.User;
import com.example.clearsolutiontesttask.repository.UserRepository;
import com.example.clearsolutiontesttask.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Value("${MIN_USER_AGE}")
    private static int MIN_USER_AGE;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto create(UserRequestDto requestDto) {
        validateNewUserUniqueFieldValues(requestDto);
        validateAdultUser(requestDto.getBirthDate());

        User user = userMapper.toUser(requestDto);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponseDto> findAllByDateRange(DateRangeDto dateRangeDto) {

        validateBirthDate(dateRangeDto);

        List<User> userByBirthDateBetween =
                userRepository.findUserByBirthDateBetween(
                        dateRangeDto.startDate(), dateRangeDto.endDate());

        return userByBirthDateBetween.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponseDto patchById(UserRequestDto userRequestDto, Long id) {
        validateAdultUser(userRequestDto.getBirthDate());
        validateExistedUserUniqueFieldValues(userRequestDto, id);

        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Can't find user with this id: %s", id)));

        userMapper.updateBook(userRequestDto, user);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponseDto putById(UserRequestDto userRequestDto, Long id) {

        validateAdultUser(userRequestDto.getBirthDate());
        validateExistedUserUniqueFieldValues(userRequestDto, id);

        User user = userMapper.toUser(userRequestDto);
        user.setId(id);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private void validateBirthDate(DateRangeDto dateRangeDto) {
        LocalDate from = dateRangeDto.startDate();
        LocalDate to = dateRangeDto.endDate();

        if (to.isBefore(from)) {
            throw new IllegalArgumentException(String.format(
                    "your end date: %s is earlier then start date: %s", to, from.toString()));
        }
    }

    private void validateAdultUser(LocalDate birthDate) {
        int years = Period.between(birthDate, LocalDate.now()).getYears();

        if (years < MIN_USER_AGE) {
            throw new InvalidFieldValueException(
                    String.format("You have to be %d or older to register, but your age is: %d",
                            MIN_USER_AGE, years));
        }
    }

    private void validateNewUserUniqueFieldValues(UserRequestDto requestDto) {
        Optional<User> userByEmail = userRepository.findUserByEmail(requestDto.getEmail());

        Optional<User> userByPhoneNumber =
                userRepository.findUserByPhoneNumber(requestDto.getPhoneNumber());

        if (userByEmail.isPresent() || userByPhoneNumber.isPresent()) {
            throw new InvalidFieldValueException(
                    "This email or phone number is already registered");
        }
    }

    private void validateExistedUserUniqueFieldValues(UserRequestDto userRequestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Can't find user with this id: %s", id)));

        boolean sameEmail = user.getEmail().equals(userRequestDto.getEmail());
        boolean emailExists = userRepository.findUserByEmail(userRequestDto.getEmail()).isPresent();

        if (sameEmail || emailExists) {
            throw new InvalidFieldValueException(
                    "This email or phone number is already registered");
        }
    }
}
