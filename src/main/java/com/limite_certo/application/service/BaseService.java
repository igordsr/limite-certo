package com.limite_certo.application.service;

import com.limite_certo.domain.exception.ValidationException;
import com.limite_certo.infrastructure.persistence.entity.BaseEntity;
import com.limite_certo.infrastructure.persistence.repository.BaseRepository;
import com.limite_certo.infrastructure.util.dto.BaseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<T extends BaseEntity, D extends BaseDTO> {
    private final BaseRepository<T, Long> repository;

    protected BaseService(BaseRepository<T, Long> repository) {
        this.repository = repository;
    }

    protected abstract T convertToEntity(D dto);

    protected abstract D convertToEntity(T entity);

    @Transactional(rollbackFor = Throwable.class)
    public D gravarEntidade(D dto) {
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

    private ValidationException notFound() {
        return new ValidationException(String.format("%s não localizado.", "Registro"));
    }
}
