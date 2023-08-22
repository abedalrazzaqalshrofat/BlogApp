package com.newagetechsoft.BlogApp.services.impl;

import com.newagetechsoft.BlogApp.exception.BlogApiException;
import com.newagetechsoft.BlogApp.model.User;
import com.newagetechsoft.BlogApp.payload.LoginDto;
import com.newagetechsoft.BlogApp.payload.RegistrationDto;
import com.newagetechsoft.BlogApp.repositories.RoleRepository;
import com.newagetechsoft.BlogApp.repositories.UserRepository;
import com.newagetechsoft.BlogApp.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     UserRepository userRepository,
                                     PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String authenticate(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User has logged successfully";
    }

    @Override
    public String registerUser(RegistrationDto registrationDto) {

        if (userRepository.existsByEmail(registrationDto.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"This email already is used");
        }

        if (userRepository.existsByUsername(registrationDto.getUsername())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"This username already is used");
        }

        User user = Optional.of(registrationDto)
                .map(it -> {
                    User temp = new User();
                    temp.setUsername(it.getUsername());
                    temp.setPassword(passwordEncoder.encode(it.getPassword()));
                    temp.setEmail(it.getEmail());
                    temp.setRoles(registrationDto.getRoles());
                    return temp;
                }).orElseThrow(() -> new IllegalArgumentException("Your registration data is wrong"));

        userRepository.save(user);

        return "Registration is created successfully";
    }
}
