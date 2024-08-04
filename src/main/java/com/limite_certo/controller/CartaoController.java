package com.limite_certo.controller;

import com.limite_certo.service.CartaoService;
import com.limite_certo.util.dto.CartaoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

}
