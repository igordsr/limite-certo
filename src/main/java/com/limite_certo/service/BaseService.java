package com.limite_certo.service;

import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.entity.BaseEntity;
import com.limite_certo.repository.BaseRepository;
import com.limite_certo.dto.BaseDTO;
import com.limite_certo.util.view.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public abstract class BaseService<T extends BaseEntity<?>, D extends BaseDTO<?>> {
    private final BaseRepository<T, Long> repository;

    protected BaseService(BaseRepository<T, Long> repository) {
        this.repository = repository;
    }

    protected abstract T convertToEntity(D dto);

    protected abstract D convertToEntity(T entity);

    protected abstract void executarValidacoesAntesDeCadastrar(D dto) throws RuntimeException;

    @JsonView(Views.Parcial.class)
    @Transactional(rollbackFor = Throwable.class)
    public D cadastrar(final D dto) {
        try {
            this.executarValidacoesAntesDeCadastrar(dto);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), Optional.of(e.getCode()));
        }
        T entidade = this.convertToEntity(dto);
        entidade = repository.save(entidade);
        return this.convertToEntity(entidade);
    }

    @Transactional(rollbackFor = Throwable.class)
    public D atualizarEntidade(final Long id, D dto) {
        final T entidade = this.convertToEntity(dto);
        T saved = repository.findById(id).orElseThrow(this::notFound);
        BeanUtils.copyProperties(entidade, saved);
        saved = repository.save(saved);
        return this.convertToEntity(saved);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void apagarPorId(Long id) {
        repository.deleteById(id);
    }

    public T getEntidade(Long id) {
        return repository.findById(id).orElseThrow(this::notFound);
    }

    private CustomException notFound() {
        return new CustomException(String.format("%s não localizado.", "Registro"));
    }
}
