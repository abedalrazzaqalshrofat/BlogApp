package com.newagetechsoft.BlogApp.repositories;

import com.newagetechsoft.BlogApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmailOrUsername(String email, String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}
