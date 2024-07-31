package com.limite_certo.infrastructure.persistence.repository;

import com.limite_certo.infrastructure.persistence.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoEntityRepository extends JpaRepository<PagamentoEntity, Long> {
}