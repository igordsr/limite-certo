package com.limite_certo.dto;

import com.limite_certo.entity.Pagamento;
import com.limite_certo.util.enums.StatusPagamento;

import java.io.Serializable;

/**
 * DTO for {@link Pagamento}
 */
public record PagamentoViewDTO(Double valor, String descricao, String metodoPagamento,
                               StatusPagamento status) implements Serializable {


    public static PagamentoViewDTO getInstanceFromEntity(final Pagamento entity) {
        return new PagamentoViewDTO(entity.getValor(), entity.getDescricao(), entity.getMetodoPagamento().getDescricao(), entity.getStatus());
    }

}