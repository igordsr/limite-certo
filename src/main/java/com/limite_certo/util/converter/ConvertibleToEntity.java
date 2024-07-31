package com.limite_certo.util.converter;

import com.limite_certo.entity.BaseEntity;

public interface ConvertibleToEntity<T extends BaseEntity<?>> {
    T toEntity();
}
