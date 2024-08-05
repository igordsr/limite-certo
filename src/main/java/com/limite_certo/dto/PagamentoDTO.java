package com.limite_certo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.entity.PagamentoEntity;
import com.limite_certo.util.validation.ValidationUtils;
import com.limite_certo.util.view.Views;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Random;

@Getter
@Setter
public class PagamentoDTO extends BaseDTO<PagamentoEntity> {
    @Schema(example = "5543 3451 4386 9263")
    @NotNull(message = "Numero do cartão não pode ser nulo.")
    @NotEmpty(message = "Numero do Cartão não pode ser em Branco.")
    @Pattern(regexp = "^[\\d\\s\\-*]+$", message = "Número do cartão deve conter apenas dígitos, espaços, hifens (-) ou asteriscos (*).")
    @JsonView(Views.Completo.class)
    private String numero;

    @Schema(example = "250.50")
    @NotNull(message = "Limite do cartão não pode ser nulo.")
    @Positive(message = "Limite do cartão deve ser um valor positivo.")
    @JsonView(Views.Completo.class)
    private Double limite;

    @Schema(example = "12/24")
    @NotNull(message = "Data de Validade do cartão não pode ser nula.")
    @NotBlank(message = "Data de Validade do cartã pode ser em Branco.")
    @Pattern(regexp = "\\d{2}/\\d{2}", message = "Data de validade deve estar no formato MM/yyyy.")
    @JsonView(Views.Completo.class)
    private String dataValidade;

    @Schema(example = "415")
    @NotNull(message = "CVV do cartão não pode ser nulo.")
    @NotBlank(message = "CVV do cartão não pode ser em branco.")
    @Pattern(regexp = "\\d{3,4}", message = "CVV do cartão deve ter 3 ou 4 dígitos numéricos.")
    @JsonView(Views.Completo.class)
    private String cvv;

    @CPF
    @Schema(example = "59694668247")
    @NotNull(message = "CPF do usuário não pode ser nulo.")
    @NotBlank(message = "CPF do usuário não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    private String cpf;

    @Schema(example = "100")
    @NotNull(message = "Valor do Pagamento não pode ser nulo.")
    @JsonView(Views.Completo.class)
    private Double valor;

    @Override
    @JsonProperty("chave_pagamento")
    @JsonView(Views.IdView.class)
    public Long getId() {
        return super.id;
    }

    @Override
    public PagamentoEntity toEntity() {
        final PagamentoEntity pagamentoEntity = new PagamentoEntity();
        final CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setId(super.id);
        cartaoDTO.setNumero(numero);
        cartaoDTO.setLimite(limite);
        cartaoDTO.setDataValidade(dataValidade);
        cartaoDTO.setCvv(cvv);
        cartaoDTO.setCpf(cpf);
        pagamentoEntity.setCartao(cartaoDTO.toEntity());
        pagamentoEntity.setDescricao("Compra de produto X" + ValidationUtils.RANDOM.nextInt());
        pagamentoEntity.setValor(valor);
        return pagamentoEntity;
    }
}