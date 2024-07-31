package com.limite_certo.util.enums;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public enum MetodoPagamento {
    CARTAO_DEBITO("D", "DEBITO"),
    CARTAO_CREDITO("C", "CREDITO");

    private String id;
    private String descricao;

    MetodoPagamento(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public static class Converter implements AttributeConverter<MetodoPagamento, String> {

        @Override
        public String convertToDatabaseColumn(MetodoPagamento metodoPagamento) {
            return metodoPagamento.getId();
        }

        @Override
        public MetodoPagamento convertToEntityAttribute(String id) {
            return Arrays.stream(MetodoPagamento.values())
                    .filter(status -> id.equals(status.id))
                    .findFirst()
                    .orElse(MetodoPagamento.CARTAO_CREDITO);
        }
    }
}
