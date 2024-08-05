package com.limite_certo.entity;

import com.limite_certo.dto.PagamentoDTO;
import com.limite_certo.util.enums.MetodoPagamento;
import com.limite_certo.util.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tb_pagamentos")
public class PagamentoEntity extends BaseEntity<PagamentoDTO> {
    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Convert(converter = MetodoPagamento.Converter.class)
    private MetodoPagamento metodoPagamento = MetodoPagamento.CARTAO_CREDITO;

    @Column(nullable = false)
    @Convert(converter = StatusPagamento.Converter.class)
    private StatusPagamento status;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private CartaoEntity cartao;

    @Override
    public PagamentoDTO toDTO() {
        final PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(id);
        pagamentoDTO.setValor(valor);
        pagamentoDTO.setCpf(cliente.getCpf());
        pagamentoDTO.setNumero(cartao.getNumero());
        pagamentoDTO.setDataValidade(cartao.getDataValidade().toString());
        pagamentoDTO.setCvv(cartao.getCvv().toString());
        pagamentoDTO.setValor(valor);
        return pagamentoDTO;
    }
}
