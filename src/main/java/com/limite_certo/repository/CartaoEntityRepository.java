package com.limite_certo.repository;

import com.limite_certo.entity.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoEntityRepository extends JpaRepository<CartaoEntity, Long> {
}