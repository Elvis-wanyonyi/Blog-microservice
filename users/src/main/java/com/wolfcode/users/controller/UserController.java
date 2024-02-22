package com.wolfcode.users.controller;

import com.wolfcode.users.model.ProfileResponse;
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


    @GetMapping("details/{userName}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserResponse myDetails(@PathVariable ("username") String username){
        return userService.myDetails(username);

    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse viewProfile(@PathVariable ("username") String username){
        return userService.viewProfile(username);
    }

    @PutMapping("/updateProfile/{username}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProfile(@PathVariable("userName") String username){
        userService.updateProfile(username);

        return "Success!!";
    }

    @DeleteMapping("/deactivate/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return "Account Deactivated";
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
}
