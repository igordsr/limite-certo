package com.limite_certo.infrastructure.persistence.entity;

import com.limite_certo.infrastructure.util.converter.ConvertibleToDTO;
import com.limite_certo.infrastructure.util.dto.BaseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity<T extends BaseDTO> implements ConvertibleToDTO<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, precision = 19)
    protected Long id;
}
