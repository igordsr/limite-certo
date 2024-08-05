package com.limite_certo.util.dto;

import com.limite_certo.dto.PagamentoViewDTO;
import com.limite_certo.entity.Pagamento;
import com.limite_certo.util.enums.MetodoPagamento;
import com.limite_certo.util.enums.StatusPagamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoViewDTOTest {

    @Test
    public void testGetInstanceFromEntity() {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(250.50);
        pagamento.setDescricao("Compra de produto X");
        pagamento.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        pagamento.setStatus(StatusPagamento.APROVADO);

        PagamentoViewDTO dto = PagamentoViewDTO.getInstanceFromEntity(pagamento);

        assertEquals(250.50, dto.valor());
        assertEquals("Compra de produto X", dto.descricao());
        assertEquals("CREDITO", dto.metodoPagamento());
        assertEquals(StatusPagamento.APROVADO, dto.status());
    }
}
