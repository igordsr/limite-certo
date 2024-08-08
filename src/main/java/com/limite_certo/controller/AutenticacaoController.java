package com.limite_certo.controller;


import com.limite_certo.config.jwt.JwtToken;
import com.limite_certo.config.jwt.JwtUserDetailsService;
import com.limite_certo.dto.UserLoginDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Autenticação")
@RequestMapping(value = "/autenticacao", produces = {"application/json"})
public class AutenticacaoController {
    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AutenticacaoController(JwtUserDetailsService jwtUserDetailsService, AuthenticationManager authenticationManager) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<JwtToken> autenticar(@RequestBody @Valid UserLoginDTO dto, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.usuario(), dto.senha());
            this.authenticationManager.authenticate(authenticationToken);
            final JwtToken tokenAuthenticated = this.jwtUserDetailsService.getTokenAuthenticated(dto.usuario());
            return ResponseEntity.ok(tokenAuthenticated);
        } catch (AuthenticationException ex) {
            log.warn("Bad Credentials from user '{}'", dto.usuario());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
