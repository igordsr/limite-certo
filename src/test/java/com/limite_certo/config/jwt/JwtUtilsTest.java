package com.limite_certo.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilsTest {

    @Test
    public void testCreateToken() {
        String username = "testUser";
        String role = "USER";

        JwtToken token = JwtUtils.createToken(username, role);

        assertNotNull(token, "Token should not be null");
        String jwtToken = token.getToken();

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JwtUtils.generateKey())
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            assertEquals(username, claims.getSubject(), "Username should match");
            assertTrue(claims.getExpiration().after(new Date()), "Token should not be expired");
            assertEquals(role, claims.get("role"), "Role should match");
        } catch (Exception e) {
            fail("Token should be a valid JWT", e);
        }
    }

    @Test
    public void testIsTokenValid_ValidToken() {
        String username = "testUser";
        String role = "USER";
        JwtToken token = JwtUtils.createToken(username, role);

        boolean isValid = JwtUtils.isTokenValid(token.getToken());

        assertTrue(isValid, "Token should be valid");
    }

    @Test
    public void testIsTokenValid_InvalidToken() {
        String invalidToken = "InvalidToken";

        boolean isValid = JwtUtils.isTokenValid(invalidToken);

        assertFalse(isValid, "Token should be invalid");
    }

    @Test
    public void testGetUsernameFromToken_ValidToken() {
        String username = "testUser";
        String role = "USER";
        JwtToken token = JwtUtils.createToken(username, role);

        String extractedUsername = JwtUtils.getUsernameFromToken(token.getToken());

        assertEquals(username, extractedUsername, "Username should match");
    }


    @Test
    public void testRefactorToken_WithBearer() {
        String token = JwtUtils.JWT_BEARER + "someToken";
        String refactoredToken = JwtUtils.refactorToken(token);

        assertEquals("someToken", refactoredToken, "Token should be correctly refactored");
    }

    @Test
    public void testRefactorToken_WithoutBearer() {
        String token = "someToken";
        String refactoredToken = JwtUtils.refactorToken(token);

        assertEquals("someToken", refactoredToken, "Token should be correctly refactored without 'Bearer '");
    }

    @Test
    public void testGetClaimsFromToken_InvalidToken() {
        String invalidToken = "InvalidToken";

        Claims claims = JwtUtils.getClaimsFromToken(invalidToken);

        assertNull(claims, "Claims should be null for an invalid token");
    }
}
