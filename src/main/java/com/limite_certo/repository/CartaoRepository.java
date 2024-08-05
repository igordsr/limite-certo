package com.limite_certo.repository;

import com.limite_certo.entity.Cartao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends BaseRepository<Cartao, Long> {
    Optional<Cartao> findByNumeroIgnoreCase(String numero);

}