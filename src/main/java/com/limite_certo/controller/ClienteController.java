package com.limite_certo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.dto.ClienteBaseDTO;
import com.limite_certo.service.ClienteService;
import com.limite_certo.util.view.Views;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Usuario")
@RequestMapping(value = "/cliente", produces = {"application/json"})
public class ClienteController extends BaseRest<ClienteBaseDTO> {

    @Autowired
    public ClienteController(ClienteService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Adicionar Novo Cliente",
            description = "Adiciona um novo cliente ao sistema com os dados fornecidos.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente criado com sucesso",
                            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{ \"id_cliente\": \"1\" }"))
                    )
            }
    )
    @JsonView(Views.IdView.class)
    public ResponseEntity<?> cadastrarEntidade(ClienteBaseDTO dto) {
        return super.cadastrarEntidade(dto);
    }
}
