package com.limite_certo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.limite_certo.entity.UserLogin}
 */
public record UserLoginDTO(
        @Schema(example = "adj2")
        @NotNull(message = "Usuario não pode ser em nulo.")
        @NotBlank(message = "Usuario não pode ser em Branco.")
        String usuario,
        @Schema(example = "adj@1234")
        @NotNull(message = "Senha não pode ser em nulo.")
        @NotBlank(message = "Senha não pode ser em Branco.")
        String senha) implements Serializable {
}