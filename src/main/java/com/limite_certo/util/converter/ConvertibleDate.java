package com.limite_certo.util.converter;

import com.limite_certo.util.validation.ValidationUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertibleDate {
    public static String convertDateToString(final LocalDate dataValidade) {
        ValidationUtils.isNull(dataValidade, "Objeto nulo");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        return formatter.format(dataValidade);
    }
}
