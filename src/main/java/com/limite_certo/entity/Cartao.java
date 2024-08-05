package com.limite_certo.entity;

import com.limite_certo.dto.CartaoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tb_cartoes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cliente_id", "numero"})
})
public class Cartao extends BaseEntity<CartaoDTO> {
    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private Double limite;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dataValidade;

    @Column(nullable = false)
    private Integer cvv;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;


    @Override
    public CartaoDTO toDTO() {
        final CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setId(super.id);
        cartaoDTO.setNumero(numero);
        cartaoDTO.setLimite(limite);
        cartaoDTO.setDataValidade(dataValidade.toString());
        cartaoDTO.setCvv(cvv.toString());
        cartaoDTO.setCpf(cliente.getCpf());
        return cartaoDTO;
    }
}
