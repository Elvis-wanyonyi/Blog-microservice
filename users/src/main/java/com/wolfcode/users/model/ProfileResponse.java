package com.wolfcode.users.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {

    private String firstName;
    private String username;
    private String email;
    // list of posts and comments
    //
}
