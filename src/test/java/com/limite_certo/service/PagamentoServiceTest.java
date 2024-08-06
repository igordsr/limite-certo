package com.limite_certo.service;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.dto.PagamentoDTO;
import com.limite_certo.dto.PagamentoViewDTO;
import com.limite_certo.entity.Cartao;
import com.limite_certo.entity.Cliente;
import com.limite_certo.entity.Pagamento;
import com.limite_certo.repository.PagamentoRepository;
import com.limite_certo.util.enums.MetodoPagamento;
import com.limite_certo.util.enums.StatusPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private CartaoService cartaoService;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertToEntity() {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setNumero("1234567890123456");
        dto.setCpf("12345678900");
        dto.setValor(100.0);
        dto.setDataValidade("12/26");
        dto.setCvv("33");

        Cartao cartao = new Cartao();
        cartao.setNumero("1234567890123456");
        cartao.setLimite(500.0);
        cartao.setDataValidade(LocalDate.of(2024, 12, 31));
        cartao.setCvv(33);

        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");

        when(cartaoService.findByNumeroIgnoreCase(dto.getNumero())).thenReturn(cartao);
        when(clienteService.findByCpf(dto.getCpf())).thenReturn(cliente);

        Pagamento pagamento = pagamentoService.convertToEntity(dto);

        assertNotNull(pagamento);
        assertEquals(cartao, pagamento.getCartao());
        assertEquals(cliente, pagamento.getCliente());
        assertEquals(MetodoPagamento.CARTAO_CREDITO, pagamento.getMetodoPagamento());
        assertEquals(StatusPagamento.APROVADO, pagamento.getStatus());
    }

    @Test
    public void testConvertToEntity_() {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setNumero("1234567890123456");
        dto.setCpf("12345678900");
        dto.setValor(100.0);
        dto.setDataValidade("12/26");
        dto.setCvv("33");

        Cartao cartao = new Cartao();
        cartao.setNumero("1234567890123456");
        cartao.setLimite(500.0);
        cartao.setDataValidade(LocalDate.of(2024, 12, 31));
        cartao.setCvv(33);

        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");

        Pagamento entity = new Pagamento();
        entity.setCartao(cartao);
        entity.setCliente(cliente);
        entity.setStatus(StatusPagamento.APROVADO);
        entity.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        entity.setDescricao("teste");
        entity.setValor(66.0);

        when(cartaoService.findByNumeroIgnoreCase(dto.getNumero())).thenReturn(cartao);
        when(clienteService.findByCpf(dto.getCpf())).thenReturn(cliente);

        PagamentoDTO pagamento = pagamentoService.convertToEntity(entity);

        assertNotNull(pagamento);
    }


    @Test
    public void testExecutarValidacoesAntesDeCadastrar_Valid() {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setNumero("1234567890123456");
        dto.setCpf("12345678900");
        dto.setValor(100.0);

        Cartao cartao = new Cartao();
        cartao.setNumero("1234567890123456");
        cartao.setLimite(500.0);

        when(cartaoService.findByNumeroIgnoreCase(dto.getNumero())).thenReturn(cartao);

        assertDoesNotThrow(() -> pagamentoService.executarValidacoesAntesDeCadastrar(dto));
    }

    @Test
    public void testExecutarValidacoesAntesDeCadastrar_Invalid() {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setNumero("1234567890123456");
        dto.setCpf("12345678900");
        dto.setValor(600.0);

        Cartao cartao = new Cartao();
        cartao.setNumero("1234567890123456");
        cartao.setLimite(500.0);

        when(cartaoService.findByNumeroIgnoreCase(dto.getNumero())).thenReturn(cartao);

        CustomException exception = assertThrows(CustomException.class, () -> pagamentoService.executarValidacoesAntesDeCadastrar(dto));
        assertEquals("Cartão não possui limite para essa transação, limite disponivel: 500.0", exception.getMessage());
    }

    @Test
    public void testConsultaPagamentosCliente() {
        Long clienteId = 1L;

        Pagamento pagamento = new Pagamento();
        // Set necessary fields for pagamento
        PagamentoViewDTO viewDTO = PagamentoViewDTO.getInstanceFromEntity(pagamento);
        when(pagamentoRepository.findByCliente_Id(clienteId)).thenReturn(List.of(pagamento));

        List<PagamentoViewDTO> result = pagamentoService.consultaPagamentosCliente(clienteId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(viewDTO, result.get(0));
    }
}
