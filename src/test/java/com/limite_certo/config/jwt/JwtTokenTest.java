package com.limite_certo.config.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JwtTokenTest {

    @Test
    public void testDefaultConstructor() {
        JwtToken jwtToken = new JwtToken();
        assertNull(jwtToken.getToken(), "Token should be null by default");
    }

    @Test
    public void testParameterizedConstructor() {
        JwtToken jwtToken = new JwtToken("myToken");
        assertEquals("myToken", jwtToken.getToken(), "Token should be 'myToken'");
    }

    @Test
    public void testSetToken() {
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken("newToken");
        assertEquals("newToken", jwtToken.getToken(), "Token should be 'newToken'");
    }

    @Test
    public void testGetToken() {
        JwtToken jwtToken = new JwtToken("anotherToken");
        assertEquals("anotherToken", jwtToken.getToken(), "Token should be 'anotherToken'");
    }
}
