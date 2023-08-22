package com.newagetechsoft.BlogApp.controller;

import com.newagetechsoft.BlogApp.payload.LoginDto;
import com.newagetechsoft.BlogApp.payload.RegistrationDto;
import com.newagetechsoft.BlogApp.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"login", "signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String result = authService.authenticate(loginDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"register", "signup"})
    public ResponseEntity<String> register(@RequestBody RegistrationDto registrationDto){
        return new ResponseEntity<>(authService.registerUser(registrationDto), HttpStatus.CREATED);
    }
}
