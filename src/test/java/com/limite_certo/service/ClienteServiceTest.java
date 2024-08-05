package com.limite_certo.service;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.dto.ClienteBaseDTO;
import com.limite_certo.entity.Cliente;
import com.limite_certo.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    private ClienteRepository repository;
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ClienteRepository.class);
        clienteService = new ClienteService(repository);
    }

    @Test
    void testExecutarValidacoesAntesDeCadastrarClienteExistente() {
        // Arrange
        ClienteBaseDTO dto = new ClienteBaseDTO();
        dto.setCpf("12345678901");
        Cliente entity = new Cliente();
        entity.setCpf("12345678901");
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.of(entity));

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> clienteService.executarValidacoesAntesDeCadastrar(dto));
        assertTrue(exception.getMessage().contains("Usuário com CPF 12345678901 já existe."));
    }

    @Test
    void testExecutarValidacoesAntesDeCadastrarClienteNovo() {
        // Arrange
        ClienteBaseDTO dto = new ClienteBaseDTO();
        dto.setCpf("12345678901");
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.empty());

        // Act
        assertDoesNotThrow(() -> clienteService.executarValidacoesAntesDeCadastrar(dto));
    }

    @Test
    void testFindByCpfClienteExistente() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        when(repository.findByCpf(cliente.getCpf())).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = clienteService.findByCpf(cliente.getCpf());

        // Assert
        assertEquals(cliente, result);
    }

    @Test
    void testFindByCpfClienteNaoExistente() {
        // Arrange
        String cpf = "12345678901";
        when(repository.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> clienteService.findByCpf(cpf));
        assertTrue(exception.getMessage().contains("Usuário com CPF 12345678901 não existe."));
    }
}
