package com.limite_certo.repository;

import com.limite_certo.entity.CartaoEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoEntityRepository extends BaseRepository<CartaoEntity, Long> {
    Optional<CartaoEntity> findByNumeroIgnoreCase(String numero);

}