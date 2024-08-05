package com.limite_certo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.service.BaseService;
import com.limite_certo.dto.BaseDTO;
import com.limite_certo.util.view.Views;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseRest<D extends BaseDTO<?>> {

    private final BaseService<?, D> service;

    protected BaseRest(BaseService<?, D> service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Adicionar Novo Registro",
            description = "Adiciona um novo registro ao sistema com os dados fornecidos.",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registro criado com sucesso",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<D> cadastrarEntidade(@RequestBody @Valid @JsonView(Views.Completo.class) final D dto) {
        return new ResponseEntity<>(this.service.cadastrar(dto), HttpStatus.OK);
    }
}
