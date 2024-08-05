package com.limite_certo.config.jwt;

import com.limite_certo.entity.UserLogin;
import com.limite_certo.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserLogin usuario = userLoginRepository.findByUsernameIgnoreCase(username);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(String username) {
        final UserLogin usuario = userLoginRepository.findByUsernameIgnoreCase(username);
        return JwtUtils.createToken(username, usuario.getRole().substring("ROLE_".length()));
    }
}
