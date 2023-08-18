package com.newagetechsoft.BlogApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    protected static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable().authorizeHttpRequests((auth) -> auth
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    protected UserDetailsService userDetailsService(){

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();

        UserDetails normalUser = User.builder()
                .username("normalUser")
                .password(passwordEncoder().encode("0000"))
                .roles("normalUser").build();

        return new InMemoryUserDetailsManager(admin,normalUser);

    }

}

