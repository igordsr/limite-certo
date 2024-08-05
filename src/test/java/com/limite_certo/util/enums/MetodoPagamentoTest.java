package com.limite_certo.util.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MetodoPagamentoTest {

    @Test
    public void testConvertToDatabaseColumn() {
        MetodoPagamento.Converter converter = new MetodoPagamento.Converter();

        assertEquals("D", converter.convertToDatabaseColumn(MetodoPagamento.CARTAO_DEBITO));
        assertEquals("C", converter.convertToDatabaseColumn(MetodoPagamento.CARTAO_CREDITO));
    }

    @Test
    public void testConvertToEntityAttribute() {
        MetodoPagamento.Converter converter = new MetodoPagamento.Converter();

        assertEquals(MetodoPagamento.CARTAO_DEBITO, converter.convertToEntityAttribute("D"));
        assertEquals(MetodoPagamento.CARTAO_CREDITO, converter.convertToEntityAttribute("C"));

        assertEquals(MetodoPagamento.CARTAO_CREDITO, converter.convertToEntityAttribute("unknown"));
    }
}
