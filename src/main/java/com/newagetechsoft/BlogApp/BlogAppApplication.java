package com.newagetechsoft.BlogApp;

import com.newagetechsoft.BlogApp.model.Role;
import com.newagetechsoft.BlogApp.model.User;
import com.newagetechsoft.BlogApp.repositories.RoleRepository;
import com.newagetechsoft.BlogApp.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class BlogAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BlogAppApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@example.com");
		user.setPassword(passwordEncoder.encode("admin"));

		Role role = new Role();
		role.setRoleName("ROLE_ADMIN");
		user.setRoles(Set.of(role));

		userRepository.save(user);
		roleRepository.save(role);

	}
}
