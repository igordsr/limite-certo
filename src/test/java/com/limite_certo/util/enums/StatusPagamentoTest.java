package com.limite_certo.util.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusPagamentoTest {

    @Test
    public void testConvertToDatabaseColumn() {
        StatusPagamento.Converter converter = new StatusPagamento.Converter();

        assertEquals("A", converter.convertToDatabaseColumn(StatusPagamento.APROVADO));
        assertEquals("R", converter.convertToDatabaseColumn(StatusPagamento.RECUSADO));
    }

    @Test
    public void testConvertToEntityAttribute() {
        StatusPagamento.Converter converter = new StatusPagamento.Converter();

        assertEquals(StatusPagamento.APROVADO, converter.convertToEntityAttribute("A"));
        assertEquals(StatusPagamento.RECUSADO, converter.convertToEntityAttribute("R"));

        assertEquals(StatusPagamento.RECUSADO, converter.convertToEntityAttribute("unknown"));
    }
}
