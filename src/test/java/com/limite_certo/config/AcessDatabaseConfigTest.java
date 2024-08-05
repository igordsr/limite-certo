package com.limite_certo.config;

import com.limite_certo.entity.UserLogin;
import com.limite_certo.repository.UserLoginRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcessDatabaseConfigTest {

    @Mock
    private UserLoginRepository userLoginRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AcessDatabaseConfig acessDatabaseConfig;

    public AcessDatabaseConfigTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitUserLogin() {
        when(passwordEncoder.encode("1234")).thenReturn("encodedPassword");

        acessDatabaseConfig.initUserLogin();

        verify(userLoginRepository).save(any(UserLogin.class));
    }
}
