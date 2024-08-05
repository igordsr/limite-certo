package com.limite_certo.repository;

import com.limite_certo.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);

}