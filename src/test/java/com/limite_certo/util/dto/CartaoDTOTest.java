package com.limite_certo.util.dto;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.dto.CartaoDTO;
import com.limite_certo.entity.Cartao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartaoDTOTest {

    @Test
    public void testToEntity_ValidData() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setNumero("5543 3451 4386 9263");
        cartaoDTO.setLimite(250.50);
        cartaoDTO.setDataValidade("12/24");
        cartaoDTO.setCvv("415");
        cartaoDTO.setCpf("59694668247");

        Cartao cartaoEntity = cartaoDTO.toEntity();

        assertEquals("5543 3451 4386 9263", cartaoEntity.getNumero());
        assertEquals(250.50, cartaoEntity.getLimite());
        assertEquals("2024-12-31", cartaoEntity.getDataValidade().toString());
        assertEquals(415, cartaoEntity.getCvv());
        assertEquals("59694668247", cartaoEntity.getCliente().getCpf());
    }

    @Test
    public void testToEntity_InvalidDataValidade() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setNumero("5543 3451 4386 9263");
        cartaoDTO.setLimite(250.50);
        cartaoDTO.setDataValidade("aaa");
        cartaoDTO.setCvv("415");
        cartaoDTO.setCpf("59694668247");

        CustomException thrown = assertThrows(CustomException.class, () -> {
            cartaoDTO.toEntity();
        });

        assertEquals("Data de validade inválida: Text '01/aaa' could not be parsed at index 3", thrown.getMessage());
    }
}
