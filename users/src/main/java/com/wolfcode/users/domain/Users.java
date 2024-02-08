package com.wolfcode.users.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String userId;
    @Column(nullable = false)
    private String firstName;
    private String lastname;
    @Column(unique = true, nullable = false, length = 10)
    private String userName;
    @Column( nullable = false, unique = true)
    private String email;
    private LocalDateTime joinedOn;

}


//DOB, phone, location,password >>>>to be embedded