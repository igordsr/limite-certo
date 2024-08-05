package com.limite_certo.entity;

import com.limite_certo.dto.ClienteBaseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    @Test
    public void testToDTO() {
        Cliente clienteEntity = new Cliente();
        clienteEntity.setId(1L);
        clienteEntity.setCpf("12345678900");
        clienteEntity.setNome("João da Silva");
        clienteEntity.setEmail("joao.silva@example.com");
        clienteEntity.setTelefone("11987654321");
        clienteEntity.setRua("Rua das Flores");
        clienteEntity.setCidade("São Paulo");
        clienteEntity.setEstado("SP");
        clienteEntity.setCep("12345678");
        clienteEntity.setPais("Brasil");

        ClienteBaseDTO clienteBaseDTO = clienteEntity.toDTO();

        assertEquals(1L, clienteBaseDTO.getId());
        assertEquals("12345678900", clienteBaseDTO.getCpf());
        assertEquals("João da Silva", clienteBaseDTO.getNome());
        assertEquals("joao.silva@example.com", clienteBaseDTO.getEmail());
        assertEquals("11987654321", clienteBaseDTO.getTelefone());
        assertEquals("Rua das Flores", clienteBaseDTO.getRua());
        assertEquals("São Paulo", clienteBaseDTO.getCidade());
        assertEquals("SP", clienteBaseDTO.getEstado());
        assertEquals("12345678", clienteBaseDTO.getCep());
        assertEquals("Brasil", clienteBaseDTO.getPais());
    }
}
