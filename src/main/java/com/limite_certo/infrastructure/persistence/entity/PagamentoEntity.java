package com.limite_certo.infrastructure.persistence.entity;

import com.limite_certo.infrastructure.util.dto.BaseDTO;
import com.limite_certo.infrastructure.util.enums.MetodoPagamento;
import com.limite_certo.infrastructure.util.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tb_pagamentos")
public class PagamentoEntity extends BaseEntity {
    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Convert(converter = MetodoPagamento.Converter.class)
    private MetodoPagamento metodoPagamento;

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
    public BaseDTO toDTO() {
        return null;
    }
}
