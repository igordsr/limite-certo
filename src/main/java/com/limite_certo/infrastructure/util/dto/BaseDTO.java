package com.limite_certo.infrastructure.util.dto;

import com.limite_certo.infrastructure.util.converter.ConvertibleToEntity;
import com.limite_certo.infrastructure.persistence.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDTO<T extends BaseEntity> implements ConvertibleToEntity<T> {
    protected Long id;

}
