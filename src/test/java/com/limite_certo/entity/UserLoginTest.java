package com.limite_certo.entity;

import com.limite_certo.dto.BaseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserLoginTest {

    @Test
    public void testCreateUserLogin() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("testUser");
        userLogin.setPassword("testPassword");
        userLogin.setRole("USER");

        assertNotNull(userLogin);
        assertEquals("testUser", userLogin.getUsername());
        assertEquals("testPassword", userLogin.getPassword());
        assertEquals("USER", userLogin.getRole());
    }

    @Test
    public void testToDTO() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("testUser");
        userLogin.setPassword("testPassword");
        userLogin.setRole("USER");

        BaseDTO<?> dto = userLogin.toDTO();

        assertNull(dto);
    }
}
