package com.Authentication.security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Authentication.security.model.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser,Long>{
    Optional<MyUser> findByUsername(String username);
}
