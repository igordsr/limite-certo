package com.limite_certo.controller;

import com.limite_certo.config.jwt.JwtToken;
import com.limite_certo.config.jwt.JwtUserDetailsService;
import com.limite_certo.dto.UserLoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AutenticacaoControllerTest {

    @Mock
    private JwtUserDetailsService jwtUserDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AutenticacaoController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAutenticar_Success() {
        UserLoginDTO dto = new UserLoginDTO("usuario", "senha");
        JwtToken expectedToken = new JwtToken("token");
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);

        when(jwtUserDetailsService.getTokenAuthenticated(dto.usuario()))
                .thenReturn(expectedToken);

        ResponseEntity<JwtToken> response = controller.autenticar(dto, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedToken, response.getBody());
    }


}
