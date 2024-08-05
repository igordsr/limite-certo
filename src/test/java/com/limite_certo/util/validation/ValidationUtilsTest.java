package com.limite_certo.util.validation;

import com.limite_certo.controller.exception.modal.CustomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilsTest {

    @Test
    public void testIsTrue_ShouldThrowCustomException_WhenExpressionIsTrue() {
        CustomException thrown = assertThrows(CustomException.class, () -> {
            ValidationUtils.isTrue(true, "Test exception message");
        });

        assertEquals("Test exception message", thrown.getMessage());
    }

    @Test
    public void testIsTrue_ShouldNotThrowException_WhenExpressionIsFalse() {
        assertDoesNotThrow(() -> {
            ValidationUtils.isTrue(false, "This message should not be used");
        });
    }
}
