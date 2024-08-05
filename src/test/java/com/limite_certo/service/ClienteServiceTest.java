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
        ClienteBaseDTO dto = new ClienteBaseDTO();
        dto.setCpf("12345678901");
        Cliente entity = new Cliente();
        entity.setCpf("12345678901");
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.of(entity));

        CustomException exception = assertThrows(CustomException.class, () -> clienteService.executarValidacoesAntesDeCadastrar(dto));
        assertTrue(exception.getMessage().contains("Usuário com CPF 12345678901 já existe."));
    }

    @Test
    void testExecutarValidacoesAntesDeCadastrarClienteNovo() {
        ClienteBaseDTO dto = new ClienteBaseDTO();
        dto.setCpf("12345678901");
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> clienteService.executarValidacoesAntesDeCadastrar(dto));
    }

    @Test
    void testFindByCpfClienteExistente() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        when(repository.findByCpf(cliente.getCpf())).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.findByCpf(cliente.getCpf());

        assertEquals(cliente, result);
    }

    @Test
    void testFindByCpfClienteNaoExistente() {
        String cpf = "12345678901";
        when(repository.findByCpf(cpf)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> clienteService.findByCpf(cpf));
        assertTrue(exception.getMessage().contains("Usuário com CPF 12345678901 não existe."));
    }

    @Test
    void testConvertToEntity() {
        // Arrange
        ClienteBaseDTO dto = new ClienteBaseDTO();
        dto.setCpf("12345678901");
        Cliente expectedEntity = new Cliente();
        expectedEntity.setCpf(dto.getCpf());

        Cliente result = clienteService.convertToEntity(dto);

        assertEquals(expectedEntity.getCpf(), result.getCpf());
    }

    @Test
    void testConvertToDTO() {
        Cliente entity = new Cliente();
        entity.setCpf("12345678901");
        ClienteBaseDTO expectedDTO = new ClienteBaseDTO();
        expectedDTO.setCpf(entity.getCpf());

        ClienteBaseDTO result = clienteService.convertToEntity(entity);

        assertEquals(expectedDTO.getCpf(), result.getCpf());
    }


    @Test
    void testExecutarValidacoesAntesDeCadastrarClienteExistente_() {
        ClienteBaseDTO dto = new ClienteBaseDTO();
        dto.setCpf("12345678901");
        Cliente entity = new Cliente();
        entity.setCpf("12345678901");
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.of(entity));

        CustomException exception = assertThrows(CustomException.class, () -> clienteService.executarValidacoesAntesDeCadastrar(dto));
        assertTrue(exception.getMessage().contains("Usuário com CPF 12345678901 já existe."));
    }

    @Test
    void testExecutarValidacoesAntesDeCadastrarClienteNovo_() {
        ClienteBaseDTO dto = new ClienteBaseDTO();
        dto.setCpf("12345678901");
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> clienteService.executarValidacoesAntesDeCadastrar(dto));
    }

}
