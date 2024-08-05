package com.limite_certo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.entity.Cartao;
import com.limite_certo.entity.Cliente;
import com.limite_certo.util.view.Views;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
public class CartaoDTO extends BaseDTO<Cartao> {
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

    @Override
    public Cartao toEntity() {
        final Cartao cartao = new Cartao();
        final Cliente cliente = new Cliente();
        try {
            cliente.setCpf(cpf);
            cartao.setNumero(numero);
            cartao.setDataValidade(parseDataValidade(dataValidade));
            cartao.setCvv(Integer.valueOf(cvv));
            cartao.setLimite(limite);
            cartao.setCliente(cliente);
        } catch (DateTimeParseException e) {
            throw new CustomException("Data de validade inválida: " + e.getMessage());
        }
        return cartao;
    }

    @Override
    @JsonProperty("id_cartao")
    @JsonView(Views.IdView.class)
    public Long getId() {
        return super.id;
    }

    private LocalDate parseDataValidade(String dataValidade) throws DateTimeParseException {
        LocalDate date = LocalDate.parse("01/" + dataValidade, DateTimeFormatter.ofPattern("dd/MM/yy"));
        return date.withDayOfMonth(date.lengthOfMonth());
    }
}
