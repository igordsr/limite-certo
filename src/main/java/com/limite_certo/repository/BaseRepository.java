package com.limite_certo.repository;

import com.limite_certo.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, K> extends JpaRepository<T, K> {

    @Override
    Optional<T> findById(K k);
}
