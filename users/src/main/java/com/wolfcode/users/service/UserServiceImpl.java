package com.wolfcode.users.service;

import com.wolfcode.users.domain.Users;
import com.wolfcode.users.model.ProfileResponse;
import com.wolfcode.users.model.UserRequest;
import com.wolfcode.users.model.UserResponse;
import com.wolfcode.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createAccount(UserRequest userRequest, String userName) {

        Users result = userRepository.findUserByUserName(userName);
        if (result != null) {

            Users user = Users.builder()
                    .userId(UUID.randomUUID().toString().substring(1, 8).toUpperCase())
                    .joinedOn(LocalDateTime.now())
                    .firstName(userRequest.getFirstName())
                    .lastname(userRequest.getLastname())
                    .userName(userRequest.getUserName())
                    .email(userRequest.getEmail())
                    .build();

            userRepository.save(user);
            log.info("New user {} joined with Email: {}  at: {}", user.getUserId(),
                    user.getEmail(), user.getJoinedOn());
        }else throw new IllegalArgumentException("User already exist");

    }

    @Override
    public void deleteUser(String userName) {
        userRepository.deleteUserByUserName(userName);
        log.info("user: {} has deactivated ", userName);
    }

    @Override
    public UserResponse myDetails(String userName) {
        Users user = userRepository.findUserByUserName(userName);

        if (user != null) {
            return UserResponse.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .firstName(user.getFirstName())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .build();
        }else throw new IllegalArgumentException("user not found");

    }

    @Override
    public void updateProfile(String userName) {
        userRepository.findByUserName(userName);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<Users> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();

    }

    private UserResponse mapToUserResponse(Users users) {
        return  UserResponse.builder()
                .userName(users.getUserName())
                .lastname(users.getLastname())
                .firstName(users.getFirstName())
                .userId(users.getUserId())
                .email(users.getEmail())
                .build();
    }

    @Override
    public ProfileResponse viewProfile(String userName) {
        Users user = userRepository.findByUserName(userName);
        return ProfileResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .userName(user.getUserName())
                .build();
    }

}
