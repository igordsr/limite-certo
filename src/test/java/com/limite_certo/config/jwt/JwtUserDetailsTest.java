package com.limite_certo.config.jwt;

import com.limite_certo.entity.UserLogin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUserDetailsTest {

    @Test
    public void testGetId() {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(1L);
        userLogin.setUsername("username");
        userLogin.setPassword("password");
        userLogin.setRole("ROLE_USER");

        JwtUserDetails userDetails = new JwtUserDetails(userLogin);

        assertEquals(1L, userDetails.getId(), "ID should be 1L");
    }

    @Test
    public void testEqualsAndHashCode() {
        UserLogin userLogin1 = new UserLogin();
        userLogin1.setId(1L);
        userLogin1.setUsername("username");
        userLogin1.setPassword("password");
        userLogin1.setRole("ROLE_USER");

        UserLogin userLogin2 = new UserLogin();
        userLogin2.setId(1L);
        userLogin2.setUsername("username");
        userLogin2.setPassword("password");
        userLogin2.setRole("ROLE_USER");

        JwtUserDetails userDetails1 = new JwtUserDetails(userLogin1);
        JwtUserDetails userDetails2 = new JwtUserDetails(userLogin2);

        UserLogin userLogin3 = new UserLogin();
        userLogin3.setId(2L);
        userLogin3.setUsername("differentUsername");
        userLogin3.setPassword("differentPassword");
        userLogin3.setRole("ROLE_ADMIN");

        JwtUserDetails userDetails3 = new JwtUserDetails(userLogin3);

        assertNotEquals(userDetails1, userDetails3, "UserDetails objects should not be equal");
        assertNotEquals(userDetails1.hashCode(), userDetails3.hashCode(), "HashCodes should not be equal");
    }

    @Test
    public void testEquals_DifferentUsuario() {
        UserLogin userLogin1 = new UserLogin();
        userLogin1.setUsername("user1");
        userLogin1.setPassword("password1");
        userLogin1.setRole("ROLE_USER");
        userLogin1.setId(1L);

        UserLogin userLogin2 = new UserLogin();
        userLogin2.setUsername("user2");
        userLogin2.setPassword("password2");
        userLogin2.setRole("ROLE_ADMIN");
        userLogin2.setId(2L);

        JwtUserDetails userDetails1 = new JwtUserDetails(userLogin1);
        JwtUserDetails userDetails2 = new JwtUserDetails(userLogin2);

        assertFalse(userDetails1.equals(userDetails2), "Equals should return false for objects with different 'usuario' attributes");
    }
}
