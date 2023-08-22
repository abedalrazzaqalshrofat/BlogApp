package com.newagetechsoft.BlogApp.payload;

import com.newagetechsoft.BlogApp.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class RegistrationDto {

    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

}
