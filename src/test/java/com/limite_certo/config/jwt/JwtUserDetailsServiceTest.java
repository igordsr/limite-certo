package com.limite_certo.config.jwt;

import com.limite_certo.entity.UserLogin;
import com.limite_certo.repository.UserLoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class JwtUserDetailsServiceTest {

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    @Mock
    private UserLoginRepository userLoginRepository;

    @Mock
    private UserLogin userLogin;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        String username = "testUser";

        when(userLoginRepository.findByUsernameIgnoreCase(username)).thenReturn(userLogin);
        when(userLogin.getUsername()).thenReturn(username);
        when(userLogin.getPassword()).thenReturn("password");
        when(userLogin.getRole()).thenReturn("ROLE_USER");

        JwtUserDetails userDetails = (JwtUserDetails) jwtUserDetailsService.loadUserByUsername(username);

        assertNotNull(userDetails, "UserDetails should not be null");
        assertEquals(username, userDetails.getUsername(), "Username should match");
    }

    @Test
    public void testGetTokenAuthenticated() {
        String username = "testUser";
        String role = "ROLE_USER";

        when(userLoginRepository.findByUsernameIgnoreCase(username)).thenReturn(userLogin);
        when(userLogin.getRole()).thenReturn(role);

        JwtToken token = jwtUserDetailsService.getTokenAuthenticated(username);

        assertNotNull(token, "Token should not be null");

    }
}
