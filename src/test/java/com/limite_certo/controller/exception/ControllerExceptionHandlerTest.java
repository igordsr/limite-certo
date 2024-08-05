package com.limite_certo.controller.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.limite_certo.controller.exception.modal.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;

    @Mock
    private HttpServletRequest request;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleJsonMappingException() {
        JsonMappingException ex = mock(JsonMappingException.class);
        when(ex.getPath()).thenReturn(List.of(new JsonMappingException.Reference("", "fieldName")));
        when(ex.getCause()).thenReturn(new Exception("Cause message"));

        ResponseEntity<CustomException> response = exceptionHandler.handleJsonMappingException(ex, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(1, response.getBody().getDetails().size());
    }

    @Test
    void testHandleMethodArgumentNotValidException() {
        FieldError fieldError = new FieldError("objectName", "fieldName", "defaultMessage");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<CustomException> response = exceptionHandler.handleMethodArgumentNotValidException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(1, response.getBody().getDetails().size());
        assertEquals("Validação falhou", response.getBody().getMessage());
    }

    @Test
    void testHandleDataAccessException_DataIntegrityViolation() {
        DataIntegrityViolationException dataIntegrityViolationException = mock(DataIntegrityViolationException.class);
        ConstraintViolationException constraintViolationException = mock(ConstraintViolationException.class);
        when(dataIntegrityViolationException.getCause()).thenReturn(constraintViolationException);

        DataAccessException ex = mock(DataAccessException.class);
        when(ex.getCause()).thenReturn(dataIntegrityViolationException);

        ResponseEntity<CustomException> response = exceptionHandler.handleDataAccessException(ex, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testHandleGlobalException() {
        Exception ex = new Exception("Some global error");

        ResponseEntity<CustomException> response = exceptionHandler.handleGlobalException(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro inesperado.", response.getBody().getMessage());
        assertEquals(1, response.getBody().getDetails().size());
    }

    @Test
    void testHandleCustomException() {
        CustomException ex = new CustomException();
        ex.setCode(HttpStatus.BAD_REQUEST.value());
        ex.setMessage("Custom error message");

        ResponseEntity<CustomException> response = exceptionHandler.handleCustomException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Custom error message", response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getCode());
    }
}
