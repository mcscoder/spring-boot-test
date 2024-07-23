package com.spring.boot.mysql.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.spring.boot.mysql.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
