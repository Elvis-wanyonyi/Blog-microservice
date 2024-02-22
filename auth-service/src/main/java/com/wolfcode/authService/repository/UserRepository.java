package com.wolfcode.authService.repository;

import com.wolfcode.authService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    Optional<User> findByUserName(String email);

    Optional<User> findByEmail(String email);
}
