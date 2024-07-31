package com.limite_certo.controller;

import com.limite_certo.service.BaseService;
import com.limite_certo.util.dto.BaseDTO;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Adicionar Novo Registro", description = "Adiciona um novo registro ao sistema com os dados fornecidos.", method = "POST")
    public ResponseEntity<D> cadastrarEntidade(@RequestBody @Valid D dto) {
        dto = this.service.cadastrar(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
