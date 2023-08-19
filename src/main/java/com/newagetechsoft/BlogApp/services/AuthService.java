package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.LoginDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    String authenticate(LoginDto loginDto);
}
