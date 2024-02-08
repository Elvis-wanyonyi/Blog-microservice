package com.wolfcode.users.service;

import com.wolfcode.users.model.ProfileResponse;
import com.wolfcode.users.model.UserRequest;
import com.wolfcode.users.model.UserResponse;

import java.util.List;

public interface UserService {
    void createAccount(UserRequest userRequest, String userName);

    void deleteUser(String userName);


    UserResponse myDetails(String userName);

    void updateProfile(String userName);

    List<UserResponse> getAllUsers();

    ProfileResponse viewProfile(String userName);
}
