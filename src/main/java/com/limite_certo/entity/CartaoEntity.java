package com.limite_certo.entity;

import com.limite_certo.util.dto.BaseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tb_cartoes")
public class CartaoEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private Double limite;

    @Column(nullable = false)
    private String dataValidade;

    @Column(nullable = false)
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @Override
    public BaseDTO toDTO() {
        return null;
    }
}
