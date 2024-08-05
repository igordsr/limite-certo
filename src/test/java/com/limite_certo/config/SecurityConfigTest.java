package com.limite_certo.config;

import com.limite_certo.config.jwt.JwtAuthorizationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SecurityConfigTest {

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSecurityFilterChain() throws Exception {
        HttpSecurity http = mock(HttpSecurity.class);

        when(http.csrf(any())).thenReturn(http);
        when(http.cors(any())).thenReturn(http);
        when(http.formLogin(any())).thenReturn(http);
        when(http.httpBasic(any())).thenReturn(http);
        when(http.authorizeHttpRequests(any())).thenReturn(http);
        when(http.sessionManagement(any())).thenReturn(http);
        when(http.addFilterBefore(any(), any())).thenReturn(http);

        SecurityFilterChain filterChain = securityConfig.securityFilterChain(http);

        assertThat(filterChain).isNull();
    }

    @Test
    public void testJwtAuthorizationFilter() {
        JwtAuthorizationFilter jwtAuthorizationFilter = securityConfig.jwtAuthorizationFilter();
        assertThat(jwtAuthorizationFilter).isNotNull();
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertThat(passwordEncoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    public void testCorsConfigurationSource() {
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        assertThat(corsConfigurationSource).isNotNull();
    }
}
