package com.limite_certo.util.validation;

import com.limite_certo.controller.exception.modal.CustomException;

public class ValidationUtils {

    public static void isTrue(boolean expression, String message) {
        if (expression) {
            throw new CustomException(message);
        }
    }
}
