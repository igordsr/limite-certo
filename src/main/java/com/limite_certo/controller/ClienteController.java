package com.limite_certo.controller;

import com.limite_certo.service.ClienteService;
import com.limite_certo.util.dto.ClienteBaseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Usuario")
@RequestMapping(value = "/cliente", produces = {"application/json"})
public class ClienteController extends BaseRest<ClienteBaseDTO> {
    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        super(service);
        this.service = service;
    }
}
