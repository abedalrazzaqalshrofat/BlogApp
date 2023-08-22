package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.LoginDto;
import com.newagetechsoft.BlogApp.payload.RegistrationDto;

public interface AuthService {
    String authenticate(LoginDto loginDto);

    String registerUser(RegistrationDto registrationDto);
}
