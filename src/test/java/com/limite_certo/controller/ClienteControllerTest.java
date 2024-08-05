package com.limite_certo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.dto.ClienteBaseDTO;
import com.limite_certo.service.ClienteService;
import com.limite_certo.util.view.Views;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @JsonView(Views.IdView.class)
    public void testCadastrarEntidade() {
        // Arrange
        ClienteBaseDTO clienteBaseDTO = new ClienteBaseDTO();
        clienteBaseDTO.setId(1L);

        when(clienteService.cadastrar(any(ClienteBaseDTO.class))).thenReturn(clienteBaseDTO);

        // Act
        ResponseEntity<ClienteBaseDTO> responseEntity = clienteController.cadastrarEntidade(clienteBaseDTO);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(clienteBaseDTO.getId(), responseEntity.getBody().getId());
    }
}
