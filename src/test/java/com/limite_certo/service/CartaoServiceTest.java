package com.limite_certo.service;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.dto.CartaoDTO;
import com.limite_certo.entity.Cartao;
import com.limite_certo.entity.Cliente;
import com.limite_certo.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartaoServiceTest {

    @Mock
    private CartaoRepository repository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.cartaoService = new CartaoService(repository, clienteService);
    }

    @Test
    void testCadastrarComQuantidadeDeCartoesExcedida() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setCpf("12345678900");
        cartaoDTO.setNumero("1234-5678-9876-5432");
        Cliente clienteEntity = new Cliente();
        Set<Cartao> cartoes = new HashSet<>();
        cartoes.add(new Cartao());
        cartoes.add(new Cartao());
        clienteEntity.setCartoes(cartoes);

        when(clienteService.findByCpf(any(String.class))).thenReturn(clienteEntity);

        CustomException thrown = assertThrows(CustomException.class, () -> cartaoService.cadastrar(cartaoDTO));
        assertEquals("O cliente com CPF 12345678900 já possui 2 cartões cadastrados", thrown.getMessage());
    }

    @Test
    void testCadastrarComCartaoJaCadastrado() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setCpf("12345678900");
        cartaoDTO.setNumero("1234-5678-9876-5432");
        Cliente clienteEntity = new Cliente();
        Cartao existingCartao = new Cartao();
        existingCartao.setNumero("1234-5678-9876-5432");
        Set<Cartao> cartoes = new HashSet<>();
        cartoes.add(existingCartao);
        clienteEntity.setCartoes(cartoes);

        when(clienteService.findByCpf(any(String.class))).thenReturn(clienteEntity);

        CustomException thrown = assertThrows(CustomException.class, () -> cartaoService.cadastrar(cartaoDTO));
        assertEquals("O cartão de credito com o numero 1234-5678-9876-5432 já está cadastrado", thrown.getMessage());
    }

    @Test
    public void testCadastrar_Success() {
        CartaoDTO dto = new CartaoDTO();
        dto.setCpf("12345678900");
        dto.setNumero("1234-5678-9876-5432");
        dto.setDataValidade("12/25");
        dto.setCvv("33");

        Cartao cartao1 = new Cartao();
        cartao1.setNumero("1234 5678 9012 3456");
        cartao1.setDataValidade(LocalDate.of(2024, 12, 12));
        cartao1.setCvv(123);
        cartao1.setLimite(1000D);

        Set<Cartao> cartoes = new HashSet<>();
        cartoes.add(cartao1);

        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        cliente.setCartoes(cartoes);

        Cartao entity = new Cartao();
        entity.setNumero("1234-5678-9876-5432");
        entity.setDataValidade(LocalDate.of(2025, 12, 12));
        entity.setCvv(33);
        entity.setCliente(cliente);

        when(clienteService.findByCpf(dto.getCpf())).thenReturn(cliente);
        when(repository.save(any(Cartao.class))).thenReturn(entity);

        CartaoDTO result = cartaoService.cadastrar(dto);

        assertAll(
                () -> assertNotNull(result),
                () -> verify(this.repository, times(1)).save(any(Cartao.class)),
                () -> assertEquals(Boolean.TRUE, result.equals(dto))
        );
    }

    @Test
    void testConvertToEntity() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setCpf("12345678900");
        cartaoDTO.setNumero("1234-5678-9876-5432");
        cartaoDTO.setDataValidade("12/25");
        cartaoDTO.setCvv("33");
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        when(clienteService.findByCpf(cartaoDTO.getCpf())).thenReturn(cliente);

        Cartao cartao = cartaoService.convertToEntity(cartaoDTO);

        assertEquals(cartaoDTO.getNumero(), cartao.getNumero());
        assertEquals(cliente, cartao.getCliente());
    }

//    @Test
//    void testConvertToDTO() {
//        Cartao cartao = new Cartao();
//        cartao.setNumero("1234-5678-9876-5432");
//        CartaoDTO cartaoDTO = new CartaoDTO();
//        cartaoDTO.setNumero(cartao.getNumero());
//        cartaoDTO.setDataValidade("12/25");
//        cartaoDTO.setCvv("33");
//
//        when(cartao.toDTO()).thenReturn(cartaoDTO);
//
//        CartaoDTO resultDTO = cartaoService.convertToEntity(cartao);
//
//        assertEquals(cartao.getNumero(), resultDTO.getNumero());
//    }

    @Test
    void testFindByNumeroIgnoreCaseCartaoNaoEncontrado() {
        String numeroCartao = "1234-5678-9876-5432";
        when(repository.findByNumeroIgnoreCase(numeroCartao)).thenReturn(Optional.empty());

        CustomException thrown = assertThrows(CustomException.class, () -> cartaoService.findByNumeroIgnoreCase(numeroCartao));
        assertEquals("Cartão de numero 1234-5678-9876-5432 não existe.", thrown.getMessage());
    }

    @Test
    void testFindByNumeroIgnoreCaseCartaoEncontrado() {
        String numeroCartao = "1234-5678-9876-5432";
        Cartao cartao = new Cartao();
        cartao.setNumero(numeroCartao);
        when(repository.findByNumeroIgnoreCase(numeroCartao)).thenReturn(Optional.of(cartao));

        Cartao resultCartao = cartaoService.findByNumeroIgnoreCase(numeroCartao);

        assertEquals(numeroCartao, resultCartao.getNumero());
    }

    @Test
    void testAtualizarEntidade_Success() {
        Long id = 1L;
        CartaoDTO dto = new CartaoDTO();
        dto.setDataValidade("12/24");
        dto.setNumero("1234-5678-9876-5432");
        dto.setLimite(45.6);
        dto.setCpf("12345678909");
        dto.setCvv("22");
        Cartao existingEntity = mock(Cartao.class);

        when(clienteService.findByCpf(any())).thenReturn(mock(Cliente.class));
        when(repository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(repository.save(existingEntity)).thenReturn(existingEntity);
        when(cartaoService.convertToEntity(existingEntity)).thenReturn(dto);

        CartaoDTO result = cartaoService.atualizarEntidade(id, dto);

        assertNotNull(result);
        verify(repository).findById(id);
        verify(repository).save(existingEntity);
    }


    @Test
    public void testApagarPorId() {
        Long id = 1L;

        cartaoService.apagarPorId(id);

        verify(repository).deleteById(id);
    }

    @Test
    public void testGetEntidade_Success() {
        Long id = 1L;
        Cartao entity = mock(Cartao.class);

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        Cartao result = cartaoService.getEntidade(id);

        assertNotNull(result);
        verify(repository).findById(id);
    }

    @Test
    public void testGetEntidade_NotFound() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> cartaoService.getEntidade(id));
        assertEquals("Registro não localizado.", exception.getMessage());
    }

}
