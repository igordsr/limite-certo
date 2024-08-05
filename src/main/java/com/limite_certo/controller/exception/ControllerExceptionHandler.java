package com.limite_certo.controller.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.limite_certo.controller.exception.modal.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<CustomException> handleJsonMappingException(JsonMappingException ex, WebRequest request) {
        CustomException err = new CustomException();
        List<String> details = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .map(fieldName -> fieldName + ": " + ex.getCause())
                .toList();
        err.setDetails(details);
        return new ResponseEntity<>(err, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomException> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> messages = new ArrayList<>();
        CustomException err = new CustomException();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String message = String.format("[%s] - %s", fieldError.getField(), fieldError.getDefaultMessage());
            messages.add(message);
        }
        err.setDetails(messages);
        err.setCode(HttpStatus.BAD_REQUEST.value());
        err.setMessage("Validação falhou");
        return new ResponseEntity<>(err, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<CustomException> handleDataAccessException(DataAccessException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        Throwable cause = ex.getCause();
        String userMessage = "Erro na tentativa de executar ação no banco de dados";
        CustomException err = new CustomException();

        if (cause instanceof DataIntegrityViolationException) {
            Throwable rootCause = cause.getCause();
            if (rootCause instanceof ConstraintViolationException) {
                for (ConstraintViolation<?> violation : ((ConstraintViolationException) rootCause).getConstraintViolations()) {
                    errors.add(violation.getMessage());
                }
            }
        } else if (cause instanceof ConstraintViolationException) {
            org.hibernate.exception.ConstraintViolationException hibernateViolation = (org.hibernate.exception.ConstraintViolationException) cause;
            SQLException sqlException = hibernateViolation.getSQLException();
            userMessage = getSqlErrorMessage(sqlException.getErrorCode());
            errors.add("Error Code: " + sqlException.getErrorCode());
            errors.add("Message: " + sqlException.getMessage());
            errors.add("SQL State: " + sqlException.getSQLState());
            errors.add("Detail Message: " + sqlException.getLocalizedMessage());
        } else {
            errors.add(cause.getMessage());
        }

        err.setCode(HttpStatus.CONFLICT.value());
        err.setMessage(userMessage);
        err.setDetails(errors);
        return new ResponseEntity<>(err, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomException> handleGlobalException(Exception ex, HttpServletRequest request) {
        CustomException err = new CustomException();
        err.setMessage("Ocorreu um erro inesperado.");
        err.setDetails(List.of(ex.getMessage()));
        err.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(err, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomException> handleCustomException(CustomException ex, HttpServletRequest request) {
        final int httpStatusCode = Optional.of(ex.getCode()).orElse(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ex.setCode(HttpStatusCode.valueOf(httpStatusCode).value());
        return new ResponseEntity<>(ex, HttpHeaders.EMPTY, HttpStatusCode.valueOf(httpStatusCode).value());
    }

    private String getSqlErrorMessage(int errorCode) {
        return switch (errorCode) {
            case 547 -> "Operação não permitida, registro em uso.";
            case 2627 -> "Operação não permitida, registro duplicado.";
            case 2601 -> "Não é permitido inserir registro duplicado.";
            default -> "Erro de acesso ao banco de dados";
        };
    }
}
