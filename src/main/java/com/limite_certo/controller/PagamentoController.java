package com.limite_certo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.dto.PagamentoViewDTO;
import com.limite_certo.service.PagamentoService;
import com.limite_certo.dto.PagamentoDTO;
import com.limite_certo.util.view.Views;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Registro do Pagamento")
@RequestMapping(value = "/pagamentos", produces = {"application/json"})
public class PagamentoController extends BaseRest<PagamentoDTO> {
    private final PagamentoService service;
    @Autowired
    public PagamentoController(PagamentoService service) {
        super(service);
        this.service = service;
    }


    @GetMapping("/cliente/{Chave}")
    @Operation(
            summary = "Consulta de Pagamentos por Cliente",
            description = "listar os pagamentos efetuados por um único cliente e mostrar a situação desse pagamento",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de Registros",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<List<PagamentoViewDTO>> consultaPagamentosCliente(@PathVariable Long Chave) {
        return new ResponseEntity<>(this.service.consultaPagamentosCliente(Chave), HttpStatus.OK);
    }

    @PostMapping()
@Operation(
    summary = "Registrar um novo pagamento",
    description = "Registrar um novo pagamento com os dados fornecidos",
    method = "POST",
    responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Pagamento registrado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagamentoDTO.class))
        )
    }
)
public ResponseEntity<PagamentoDTO> registrarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO) {
    return new ResponseEntity<>(this.service.cadastrar(pagamentoDTO), HttpStatus.CREATED);
}

}
