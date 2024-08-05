package com.limite_certo.service;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.entity.CartaoEntity;
import com.limite_certo.entity.ClienteEntity;
import com.limite_certo.repository.CartaoEntityRepository;
import com.limite_certo.dto.CartaoDTO;
import com.limite_certo.util.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CartaoService extends BaseService<CartaoEntity, CartaoDTO> {
    private final CartaoEntityRepository repository;
    private final ClienteService clienteService;

    @Autowired
    protected CartaoService(CartaoEntityRepository repository, ClienteService clienteService) {
        super(repository);
        this.repository = repository;
        this.clienteService = clienteService;
    }

    @Override
    protected CartaoEntity convertToEntity(CartaoDTO dto) {
        final ClienteEntity clienteEntity = this.clienteService.findByCpf(dto.getCpf());
        CartaoEntity cartaoEntity = dto.toEntity();
        cartaoEntity.setCliente(clienteEntity);
        return cartaoEntity;
    }

    @Override
    protected CartaoDTO convertToEntity(CartaoEntity entity) {
        return entity.toDTO();
    }

    @Override
    protected void executarValidacoesAntesDeCadastrar(CartaoDTO dto) {
        final ClienteEntity clienteEntity = this.clienteService.findByCpf(dto.getCpf());
        this.validarQuantidadeDeCartaoUsuario(clienteEntity.getCartoes(), dto);
        this.validarCartaoCadastrados(clienteEntity.getCartoes(), dto);
    }

    public CartaoEntity findByNumeroIgnoreCase(final String numeroCartao) {
        return this.repository.findByNumeroIgnoreCase(numeroCartao)
                .orElseThrow(() -> new CustomException("Cartão de numero " + numeroCartao + " não existe."));

    }

    private void validarQuantidadeDeCartaoUsuario(final Set<CartaoEntity> cartoes, final CartaoDTO dto) {
        try {
            ValidationUtils.isTrue(cartoes.size() >= 2, String.format("O cliente com CPF %s já possui 2 cartões cadastrados", dto.getCpf()));
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), Optional.of(403));
        }
    }

    private void validarCartaoCadastrados(final Set<CartaoEntity> cartoes, final CartaoDTO dto) {
        try {
            ValidationUtils.isTrue(cartoes.stream().anyMatch(cartao -> cartao.getNumero().equals(dto.getNumero())), String.format("O cartão de credito com o numero %s já está cadastrado", dto.getNumero()));
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), Optional.of(500));
        }
    }

}
