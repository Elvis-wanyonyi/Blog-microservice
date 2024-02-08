package com.wolfcode.users.controller;

import com.wolfcode.users.model.ProfileResponse;
import com.wolfcode.users.model.UserRequest;
import com.wolfcode.users.model.UserResponse;
import com.wolfcode.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAccount(@RequestBody UserRequest userRequest, String userName){
        userService.createAccount(userRequest, userName);
        return "Account created successfully";
    }

    @GetMapping("details/{userName}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserResponse myDetails(@PathVariable ("userName") String userName){
        return userService.myDetails(userName);

    }

    @GetMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse viewProfile(@PathVariable ("userName") String userName){
        return userService.viewProfile(userName);
    }

    @PutMapping("/updateProfile/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProfile(@PathVariable("userName") String userName){
        userService.updateProfile(userName);

        return "Success!!";
    }

    @DeleteMapping("/deactivate/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
        return "Account Deactivated";
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
}
