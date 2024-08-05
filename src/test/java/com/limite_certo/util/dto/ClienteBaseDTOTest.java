package com.limite_certo.util.dto;

import com.limite_certo.dto.ClienteBaseDTO;
import com.limite_certo.entity.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteBaseDTOTest {

    @Test
    public void testToEntity_ValidData() {
        ClienteBaseDTO clienteDTO = new ClienteBaseDTO();
        clienteDTO.setCpf("59694668247");
        clienteDTO.setNome("João da Silva");
        clienteDTO.setEmail("joao@gmail.com");
        clienteDTO.setTelefone("+55 11 91234-5678");
        clienteDTO.setRua("Rua A");
        clienteDTO.setCidade("São Paulo");
        clienteDTO.setEstado("São Paulo");
        clienteDTO.setCep("01001-000");
        clienteDTO.setPais("Brasil");

        Cliente clienteEntity = clienteDTO.toEntity();

        assertEquals("59694668247", clienteEntity.getCpf());
        assertEquals("João da Silva", clienteEntity.getNome());
        assertEquals("joao@gmail.com", clienteEntity.getEmail());
        assertEquals("+55 11 91234-5678", clienteEntity.getTelefone());
        assertEquals("Rua A", clienteEntity.getRua());
        assertEquals("São Paulo", clienteEntity.getCidade());
        assertEquals("São Paulo", clienteEntity.getEstado());
        assertEquals("01001-000", clienteEntity.getCep());
        assertEquals("Brasil", clienteEntity.getPais());
    }
}
