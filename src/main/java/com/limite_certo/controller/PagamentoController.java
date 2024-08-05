package com.limite_certo.controller;

import com.limite_certo.service.PagamentoService;
import com.limite_certo.util.dto.PagamentoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Registro do Pagamento")
@RequestMapping(value = "/pagamentos", produces = {"application/json"})
public class PagamentoController extends BaseRest<PagamentoDTO> {

    @Autowired
    public PagamentoController(PagamentoService service) {
        super(service);
    }

}
