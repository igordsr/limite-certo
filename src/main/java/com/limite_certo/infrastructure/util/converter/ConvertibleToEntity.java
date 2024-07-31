package com.limite_certo.infrastructure.util.converter;

import com.limite_certo.infrastructure.persistence.entity.BaseEntity;

public interface ConvertibleToEntity<T extends BaseEntity> {
    T toEntity();
}
