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

//    @Test
//    void testCadastrarComSucesso() {
//        // Arrange
//        CartaoDTO cartaoDTO = new CartaoDTO();
//        cartaoDTO.setLimite(250.50);
//        cartaoDTO.setCvv("415");
//        cartaoDTO.setCpf("12345678900");
//        cartaoDTO.setNumero("1234-5678-9876-5432");
//        cartaoDTO.setDataValidade("12/24");
//        Cliente clienteEntity = new Cliente();
//        clienteEntity.setCartoes(new HashSet<>());
//        Cartao cartaoEntity = new Cartao();
//        cartaoEntity.setId(1L);
//        cartaoEntity.setCliente(clienteEntity);
//
//        when(clienteService.findByCpf(any(String.class))).thenReturn(clienteEntity);
//        when(repository.save(any(Cartao.class))).thenReturn(cartaoEntity);
//
//        // Act
//        CartaoDTO result = cartaoService.cadastrar(cartaoDTO);
//
//        // Assert
//        assertEquals(cartaoEntity.getId(), result.getId());
//    }

    @Test
    void testCadastrarComQuantidadeDeCartoesExcedida() {
        // Arrange
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setCpf("12345678900");
        cartaoDTO.setNumero("1234-5678-9876-5432");
        Cliente clienteEntity = new Cliente();
        Set<Cartao> cartoes = new HashSet<>();
        cartoes.add(new Cartao());
        cartoes.add(new Cartao());
        clienteEntity.setCartoes(cartoes);

        when(clienteService.findByCpf(any(String.class))).thenReturn(clienteEntity);

        // Act & Assert
        CustomException thrown = assertThrows(CustomException.class, () -> cartaoService.cadastrar(cartaoDTO));
        assertEquals("O cliente com CPF 12345678900 já possui 2 cartões cadastrados", thrown.getMessage());
    }

    @Test
    void testCadastrarComCartaoJaCadastrado() {
        // Arrange
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

        // Act & Assert
        CustomException thrown = assertThrows(CustomException.class, () -> cartaoService.cadastrar(cartaoDTO));
        assertEquals("O cartão de credito com o numero 1234-5678-9876-5432 já está cadastrado", thrown.getMessage());
    }
}
