package com.limite_certo.util.enums;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public enum StatusPagamento {
    APROVADO("A", "APROVADO"),
    RECUSADO("R", "RECUSADO");

    private String id;
    private String descricao;

    StatusPagamento(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public static class Converter implements AttributeConverter<StatusPagamento, String> {

        @Override
        public String convertToDatabaseColumn(StatusPagamento metodoPagamento) {
            return metodoPagamento.getId();
        }

        @Override
        public StatusPagamento convertToEntityAttribute(String id) {
            return Arrays.stream(StatusPagamento.values())
                    .filter(status -> id.equals(status.id))
                    .findFirst()
                    .orElse(StatusPagamento.RECUSADO);
        }
    }
}
