package com.limite_certo.config;

import com.limite_certo.entity.UserLogin;
import com.limite_certo.repository.UserLoginRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AcessDatabaseConfig {
    private final UserLoginRepository userLoginRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AcessDatabaseConfig(UserLoginRepository userLoginRepository, PasswordEncoder passwordEncoder) {
        this.userLoginRepository = userLoginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initUserLogin() {
        final UserLogin userLogin = new UserLogin();
        userLogin.setUsername("adj2");
        userLogin.setRole("ROLE_ADMIN");
        userLogin.setPassword(passwordEncoder.encode("adj@1234"));
        userLoginRepository.save(userLogin);
    }
}
