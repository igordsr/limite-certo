package com.limite_certo.entity;

import com.limite_certo.dto.CartaoDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaoTest {

    @Test
    public void testToDTO() {
        Cartao cartaoEntity = new Cartao();
        cartaoEntity.setId(1L);
        cartaoEntity.setNumero("1234567890123456");
        cartaoEntity.setLimite(5000.00);
        cartaoEntity.setDataValidade(LocalDate.of(2025, 12, 31));
        cartaoEntity.setCvv(123);

        Cliente clienteEntity = Mockito.mock(Cliente.class);
        Mockito.when(clienteEntity.getCpf()).thenReturn("12345678900");
        cartaoEntity.setCliente(clienteEntity);

        CartaoDTO cartaoDTO = cartaoEntity.toDTO();

        assertEquals(1L, cartaoDTO.getId());
        assertEquals("1234567890123456", cartaoDTO.getNumero());
        assertEquals(5000.00, cartaoDTO.getLimite());
        assertEquals("2025-12-31", cartaoDTO.getDataValidade());
        assertEquals("123", cartaoDTO.getCvv());
        assertEquals("12345678900", cartaoDTO.getCpf());
    }
}
