package com.limite_certo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.dto.CartaoDTO;
import com.limite_certo.service.CartaoService;
import com.limite_certo.util.view.Views;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Cartão de Credito")
@RequestMapping(value = "/cartao", produces = {"application/json"})
public class CartaoController extends BaseRest<CartaoDTO> {

    @Autowired
    public CartaoController(CartaoService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Adicionar Novo Cartão de Credito",
            description = "Adiciona um novo Cartão de Credito ao sistema com os dados fornecidos.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cartão de Credito criado com sucesso"
                    )
            }
    )
    @JsonView(Views.None.class)
    public ResponseEntity<Void> cadastrarEntidade(@JsonView(Views.Completo.class) CartaoDTO dto) {
        super.cadastrarEntidade(dto);
        return ResponseEntity.ok().build();
    }

}
