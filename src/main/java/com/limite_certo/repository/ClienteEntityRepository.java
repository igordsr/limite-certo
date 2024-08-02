package com.limite_certo.repository;

import com.limite_certo.entity.ClienteEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteEntityRepository extends BaseRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByCpf(String cpf);

}