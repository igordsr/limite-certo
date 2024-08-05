package com.limite_certo.util.dto;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.dto.PagamentoDTO;
import com.limite_certo.entity.Pagamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PagamentoDTOTest {

    @Test
    public void testToEntity_ValidData() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setNumero("5543 3451 4386 9263");
        pagamentoDTO.setLimite(250.50);
        pagamentoDTO.setDataValidade("12/24");
        pagamentoDTO.setCvv("415");
        pagamentoDTO.setCpf("59694668247");
        pagamentoDTO.setValor(100.00);

        Pagamento pagamentoEntity = pagamentoDTO.toEntity();

        assertEquals("5543 3451 4386 9263", pagamentoEntity.getCartao().getNumero());
        assertEquals(250.50, pagamentoEntity.getCartao().getLimite());
        assertEquals("2024-12-31", pagamentoEntity.getCartao().getDataValidade().toString());
        assertEquals("415", pagamentoEntity.getCartao().getCvv().toString());
        assertEquals("59694668247", pagamentoEntity.getCartao().getCliente().getCpf());
        assertEquals(100.00, pagamentoEntity.getValor());
    }

    @Test
    public void testToEntity_InvalidData() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setNumero("5543 3451 4386 9263");
        pagamentoDTO.setLimite(250.50);
        pagamentoDTO.setDataValidade("invalid_date"); // Data inválida
        pagamentoDTO.setCvv("415");
        pagamentoDTO.setCpf("59694668247");
        pagamentoDTO.setValor(100.00);

        CustomException thrown = assertThrows(CustomException.class, () -> {
            pagamentoDTO.toEntity();
        });

        assertTrue(thrown.getMessage().contains("Data de validade inválida"));
    }
}
