package com.wolfcode.users.repository;

import com.wolfcode.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {


    Users findUserByUserName(String userName);

    void deleteUserByUserName(String userName);



    Users findByUserName(String userName);
}
