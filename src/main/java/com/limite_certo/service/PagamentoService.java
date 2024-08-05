package com.limite_certo.service;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.entity.CartaoEntity;
import com.limite_certo.entity.ClienteEntity;
import com.limite_certo.entity.PagamentoEntity;
import com.limite_certo.repository.PagamentoEntityRepository;
import com.limite_certo.util.dto.PagamentoDTO;
import com.limite_certo.util.enums.MetodoPagamento;
import com.limite_certo.util.enums.StatusPagamento;
import com.limite_certo.util.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService extends BaseService<PagamentoEntity, PagamentoDTO> {
    private final PagamentoEntityRepository repository;
    private final CartaoService cartaoService;

    private final ClienteService clienteService;

    @Autowired
    protected PagamentoService(PagamentoEntityRepository repository, CartaoService cartaoService, ClienteService clienteService) {
        super(repository);
        this.repository = repository;
        this.cartaoService = cartaoService;
        this.clienteService = clienteService;
    }


    @Override
    protected PagamentoEntity convertToEntity(PagamentoDTO dto) {
        final PagamentoEntity entity = dto.toEntity();
        final CartaoEntity cartao = this.cartaoService.findByNumeroIgnoreCase(dto.getNumero());
        final ClienteEntity cliente = this.clienteService.findByCpf(dto.getCpf());
        entity.setCartao(cartao);
        entity.setCliente(cliente);
        entity.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        entity.setStatus(StatusPagamento.APROVADO);
        return entity;
    }

    @Override
    protected PagamentoDTO convertToEntity(PagamentoEntity entity) {
        return entity.toDTO();
    }

    @Override
    protected void executarValidacoesAntesDeCadastrar(PagamentoDTO dto) throws RuntimeException {
        final CartaoEntity cartao = this.cartaoService.findByNumeroIgnoreCase(dto.getNumero());
        this.validarLimiteCartao(cartao, dto);
    }

    private void validarLimiteCartao(final CartaoEntity cartao, PagamentoDTO dto) {
        try {
            final Double creditoUtilizado = this.repository.somarValoresPorCartao(cartao.getNumero(), cartao.getDataValidade(), cartao.getCvv());
            final Double limiteRestante = cartao.getLimite() - creditoUtilizado;
            ValidationUtils.isTrue(dto.getValor() > limiteRestante, String.format("Cartão não possui limite para essa transação, limite disponivel: %s", limiteRestante));

        } catch (Exception e) {
            throw new CustomException(e.getMessage(), Optional.of(402));
        }
    }
}
