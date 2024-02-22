package com.wolfcode.users.service;

import com.wolfcode.users.model.ProfileResponse;
import com.wolfcode.users.model.UserResponse;

import java.util.List;

public interface UserService {
//    void createAccount(UserRequest userRequest, String userName);

    void deleteUser(String username);


    UserResponse myDetails(String username);

    void updateProfile(String username);

    List<UserResponse> getAllUsers();

    ProfileResponse viewProfile(String username);
}
