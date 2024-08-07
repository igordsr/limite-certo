package com.limite_certo.controller;

import com.limite_certo.dto.PagamentoViewDTO;
import com.limite_certo.service.PagamentoService;
import com.limite_certo.util.enums.StatusPagamento;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PagamentoControllerTest {

    @Mock
    private PagamentoService service;

    @InjectMocks
    private PagamentoController controller;

    public PagamentoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultaPagamentosCliente() {
        // Arrange
        Long chave = 1L;
        List<PagamentoViewDTO> pagamentoViewDTOList = new ArrayList<>();
        PagamentoViewDTO dto = new PagamentoViewDTO(6.6, "teste", "Cartão", StatusPagamento.APROVADO);
        pagamentoViewDTOList.add(dto);

        when(service.consultaPagamentosCliente(chave)).thenReturn(pagamentoViewDTOList);

        // Act
        ResponseEntity<List<PagamentoViewDTO>> response = controller.consultaPagamentosCliente(chave);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pagamentoViewDTOList, response.getBody());
    }

    @Test
    public void testRegistrarPagamento() {
        // Arrange
        Long chave = 1L;
        List<PagamentoViewDTO> pagamentoViewDTOList = new ArrayList<>();
        PagamentoViewDTO dto = new PagamentoViewDTO(6.6, "teste", "Cartão", StatusPagamento.APROVADO);
        pagamentoViewDTOList.add(dto);

        when(service.consultaPagamentosCliente(chave)).thenReturn(pagamentoViewDTOList);

        // Act
        ResponseEntity<List<PagamentoViewDTO>> response = controller.consultaPagamentosCliente(chave);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pagamentoViewDTOList, response.getBody());
    }
}

