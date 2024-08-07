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
import com.limite_certo.util.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService extends BaseService<Pagamento, PagamentoDTO> {
    private final PagamentoRepository repository;
    private final CartaoService cartaoService;
    private final ClienteService clienteService;

    @Autowired
    protected PagamentoService(PagamentoRepository repository, CartaoService cartaoService, ClienteService clienteService) {
        super(repository);
        this.repository = repository;
        this.cartaoService = cartaoService;
        this.clienteService = clienteService;
    }


    @Override
    protected Pagamento convertToEntity(PagamentoDTO dto) {
        final Pagamento entity = dto.toEntity();
        final Cartao cartao = this.cartaoService.findByNumeroIgnoreCase(dto.getNumero());
        final Cliente cliente = this.clienteService.findByCpf(dto.getCpf());
        entity.setCartao(cartao);
        entity.setCliente(cliente);
        entity.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        entity.setStatus(StatusPagamento.APROVADO);
        return entity;
    }

    @Override
    protected PagamentoDTO convertToEntity(Pagamento entity) {
        return entity.toDTO();
    }

    @Override
    protected void executarValidacoesAntesDeCadastrar(PagamentoDTO dto) throws RuntimeException {
        final Cartao cartao = this.cartaoService.findByNumeroIgnoreCase(dto.getNumero());
        this.validarLimiteCartao(cartao, dto);
        this.validarSeOCartaoExiste(cartao.getNumero(), dto);
    }

    private void validarLimiteCartao(final Cartao cartao, PagamentoDTO dto) {
        try {
            final Double creditoUtilizado = this.repository.somarValoresPorCartao(cartao.getNumero(), cartao.getDataValidade(), cartao.getCvv());
            final Double limiteRestante = cartao.getLimite() - creditoUtilizado;
            ValidationUtils.isTrue(dto.getValor() > limiteRestante, String.format("Cartão não possui limite para essa transação, limite disponivel: %s", limiteRestante));

        } catch (Exception e) {
            throw new CustomException(e.getMessage(), Optional.of(402));
        }
    }

    private void validarSeOCartaoExiste(final String numero, PagamentoDTO dto) {
        final Cartao cartao = this.cartaoService.findByNumeroIgnoreCase(numero);
        ValidationUtils.isFalse(Objects.equals(cartao.getCliente().getCpf(), dto.getCpf()), "Cartão não localizado para este Cliente.");
    }
    public  PagamentoDTO cadastrar(final PagamentoDTO dto) {
        try {
            this.executarValidacoesAntesDeCadastrar(dto);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), Optional.of(e.getCode()));
        }
        Pagamento entidade = this.convertToEntity(dto);
        entidade = repository.save(entidade);
        return this.convertToEntity(entidade);
    }

    public List<PagamentoViewDTO> consultaPagamentosCliente(Long chave) {
        return this.repository.findByCliente_Id(chave).stream().map(PagamentoViewDTO::getInstanceFromEntity).toList();
    }
}
