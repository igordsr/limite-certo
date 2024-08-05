package com.limite_certo.util.validation;

import com.limite_certo.controller.exception.modal.CustomException;

import java.util.Random;

public class ValidationUtils {
    public static final Random RANDOM = new Random();

    public static void isTrue(boolean expression, String message) {
        if (expression) {
            throw new CustomException(message);
        }
    }

    private ValidationUtils() {

    }
}
