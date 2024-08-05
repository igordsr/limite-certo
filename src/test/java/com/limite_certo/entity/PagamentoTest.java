package com.limite_certo.entity;

import com.limite_certo.dto.PagamentoDTO;
import com.limite_certo.util.enums.MetodoPagamento;
import com.limite_certo.util.enums.StatusPagamento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoTest {

    @Test
    public void testToDTO() {
        Cliente clienteEntity = new Cliente();
        clienteEntity.setCpf("12345678900");

        Cartao cartaoEntity = new Cartao();
        cartaoEntity.setNumero("1234567890123456");
        cartaoEntity.setDataValidade(LocalDate.of(2025, 12, 31));
        cartaoEntity.setCvv(123);

        Pagamento pagamentoEntity = new Pagamento();
        pagamentoEntity.setId(1L);
        pagamentoEntity.setValor(250.00);
        pagamentoEntity.setDescricao("Pagamento de teste");
        pagamentoEntity.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        pagamentoEntity.setStatus(StatusPagamento.APROVADO);
        pagamentoEntity.setCliente(clienteEntity);
        pagamentoEntity.setCartao(cartaoEntity);

        PagamentoDTO pagamentoDTO = pagamentoEntity.toDTO();

        assertEquals(1L, pagamentoDTO.getId());
        assertEquals(250.00, pagamentoDTO.getValor());
        assertEquals("12345678900", pagamentoDTO.getCpf());
        assertEquals("1234567890123456", pagamentoDTO.getNumero());
        assertEquals("2025-12-31", pagamentoDTO.getDataValidade());
        assertEquals("123", pagamentoDTO.getCvv());
        assertEquals(250.00, pagamentoDTO.getValor());
    }
}
