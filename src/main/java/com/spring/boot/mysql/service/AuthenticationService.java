package com.spring.boot.mysql.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.boot.mysql.dto.LoginUserDto;
import com.spring.boot.mysql.dto.RegisterUserDto;
import com.spring.boot.mysql.model.User;
import com.spring.boot.mysql.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
            AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setFullName(input.fullName());
        user.setEmail(input.email());
        user.setPassword(passwordEncoder.encode(input.password()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.email(), input.password()));

        return userRepository.findByEmail(input.email()).orElseThrow();
    }
}
