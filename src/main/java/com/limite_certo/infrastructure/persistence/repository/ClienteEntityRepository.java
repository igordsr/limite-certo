package com.limite_certo.infrastructure.persistence.repository;

import com.limite_certo.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteEntityRepository extends BaseRepository<ClienteEntity, Long> {
}