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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

}
