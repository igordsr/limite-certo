package com.limite_certo.controller.exception.modal;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomExceptionTest {

    @Test
    void testCustomException() {
        CustomException exception = new CustomException("Test message", Optional.of(400));

        assertEquals("Test message", exception.getMessage());
        assertEquals(400, exception.getCode());
        assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), exception.getTimestamp().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void testCustomExceptionWithoutCode() {
        CustomException exception = new CustomException("Test message");

        assertEquals("Test message", exception.getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getCode());
        assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), exception.getTimestamp().get(Calendar.DAY_OF_MONTH));
    }
}
