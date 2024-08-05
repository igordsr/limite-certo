package com.limite_certo.config.jwt;

import com.limite_certo.entity.UserLogin;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;

public class JwtUserDetails extends User {
    private final UserLogin usuario;

    public JwtUserDetails(UserLogin usuario) {
        super(usuario.getUsername(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRole()));
        this.usuario = usuario;
    }

    public Long getId() {
        return usuario.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JwtUserDetails that = (JwtUserDetails) o;
        return Objects.equals(usuario, that.usuario);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usuario);
    }
}
