package com.tinyinventory.app.repo;

import com.tinyinventory.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    //SELECT * FROM USERS WHERE username = username;  //SQL
    //@Query("SELECT u FROM Users u WHERE u.username = ?1") //Spring JPA
    Optional<Users> findUserByUsername(String username);

}
