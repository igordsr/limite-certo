package com.limite_certo.dto;

import com.limite_certo.entity.PagamentoEntity;
import com.limite_certo.util.enums.StatusPagamento;

import java.io.Serializable;

/**
 * DTO for {@link com.limite_certo.entity.PagamentoEntity}
 */
public record PagamentoViewDTO(Double valor, String descricao, String metodoPagamento,
                               StatusPagamento status) implements Serializable {


    public static PagamentoViewDTO getInstanceFromEntity(final PagamentoEntity entity) {
        return new PagamentoViewDTO(entity.getValor(), entity.getDescricao(), entity.getMetodoPagamento().getDescricao(), entity.getStatus());
    }

}