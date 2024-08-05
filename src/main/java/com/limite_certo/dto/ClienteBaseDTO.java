package com.limite_certo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.entity.Cliente;
import com.limite_certo.util.view.Views;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class ClienteBaseDTO extends BaseDTO<Cliente> {
    @CPF
    @Schema(example = "59694668247")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ser válido")
    @NotNull(message = "CPF do usuario não pode ser em nulo.")
    @NotBlank(message = "CPF do usuario não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    String cpf;

    @Schema(example = "João da Silva")
    @NotNull(message = "Nome do usuario não pode ser em nulo.")
    @NotBlank(message = "Nome do usuario não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    String nome;

    @Schema(example = "joão@gmail.com")
    @Email(message = "O e-mail deve ser válido")
    @NotNull(message = "O e-mail do usuario não pode ser em nulo.")
    @NotBlank(message = "O e-mail do usuario não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    String email;

    @Schema(example = "+55 11 91234-5678")
    @NotNull(message = "O Telefone não pode ser em nulo.")
    @NotBlank(message = "O Telefone não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    String telefone;

    @Schema(example = "Rua A")
    @NotNull(message = "O Rua não pode ser em nulo.")
    @NotBlank(message = "O Rua não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    String rua;

    @Schema(example = "São Paulo")
    @NotNull(message = "O Cidade não pode ser em nulo.")
    @NotBlank(message = "O Cidade não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    String cidade;

    @Schema(example = "São Paulo")
    @NotNull(message = "O Estado não pode ser em nulo.")
    @NotBlank(message = "O Estado não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    String estado;

    @Schema(example = "01001-000")
    @NotNull(message = "O CEP não pode ser em nulo.")
    @NotBlank(message = "O CEP não pode ser em Branco.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ser no formato 00000-000")
    @JsonView(Views.Completo.class)
    String cep;

    @Schema(example = "Brasil")
    @NotNull(message = "O Pais não pode ser em nulo.")
    @NotBlank(message = "O Pais não pode ser em Branco.")
    @JsonView(Views.Completo.class)
    String pais;

    @Override
    @JsonProperty("id_cliente")
    @JsonView(Views.IdView.class)
    public Long getId() {
        return super.id;
    }

    @Override
    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setRua(rua);
        cliente.setCidade(cidade);
        cliente.setEstado(estado);
        cliente.setCep(cep);
        cliente.setPais(pais);
        return cliente;
    }
}