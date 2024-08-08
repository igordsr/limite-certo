package com.limite_certo.controller;

import com.limite_certo.dto.CartaoDTO;
import com.limite_certo.service.CartaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CartaoControllerTest {

    @InjectMocks
    private CartaoController cartaoController;

    @Mock
    private CartaoService cartaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarEntidade() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setNumero("1234 5678 9876 5432");
        cartaoDTO.setLimite(1000.00);
        cartaoDTO.setDataValidade("12/24");
        cartaoDTO.setCvv("123");
        cartaoDTO.setCpf("59694668247");

        when(cartaoService.cadastrar(cartaoDTO)).thenReturn(cartaoDTO);

        ResponseEntity<?> response = cartaoController.cadastrarEntidade(cartaoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(cartaoDTO, response.getBody());
    }
}
