package com.limite_certo.service;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.entity.Cartao;
import com.limite_certo.entity.Cliente;
import com.limite_certo.repository.CartaoRepository;
import com.limite_certo.dto.CartaoDTO;
import com.limite_certo.util.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CartaoService extends BaseService<Cartao, CartaoDTO> {
    private final CartaoRepository repository;
    private final ClienteService clienteService;

    @Autowired
    protected CartaoService(CartaoRepository repository, ClienteService clienteService) {
        super(repository);
        this.repository = repository;
        this.clienteService = clienteService;
    }

    @Override
    protected Cartao convertToEntity(CartaoDTO dto) {
        final Cliente cliente = this.clienteService.findByCpf(dto.getCpf());
        Cartao cartao = dto.toEntity();
        cartao.setCliente(cliente);
        return cartao;
    }

    @Override
    protected CartaoDTO convertToEntity(Cartao entity) {
        return entity.toDTO();
    }

    @Override
    protected void executarValidacoesAntesDeCadastrar(CartaoDTO dto) {
        dto.setNumero(dto.getNumero().replace(" ", ""));
        final Cliente cliente = this.clienteService.findByCpf(dto.getCpf());
        this.validarQuantidadeDeCartaoUsuario(cliente.getCartoes(), dto);
        this.validarCartaoCadastrados(cliente.getCartoes(), dto);
    }

    public Cartao findByNumeroIgnoreCase(final String numeroCartao) {
        String replace = numeroCartao.replace(" ", "");
        return this.repository.findByNumeroIgnoreCase(replace)
                .orElseThrow(() -> new CustomException("Cartão de numero " + replace + " não existe."));

    }

    private void validarQuantidadeDeCartaoUsuario(final Set<Cartao> cartoes, final CartaoDTO dto) {
        try {
            ValidationUtils.isTrue(cartoes.size() >= 2, String.format("O cliente com CPF %s já possui 2 cartões cadastrados", dto.getCpf()));
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), Optional.of(403));
        }
    }

    private void validarCartaoCadastrados(final Set<Cartao> cartoes, final CartaoDTO dto) {
        try {
            ValidationUtils.isTrue(cartoes.stream().anyMatch(cartao -> cartao.getNumero().equals(dto.getNumero())), String.format("O cartão de credito com o numero %s já está cadastrado", dto.getNumero()));
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), Optional.of(500));
        }
    }

}
