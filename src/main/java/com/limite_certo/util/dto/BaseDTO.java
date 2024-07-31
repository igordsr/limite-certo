package com.limite_certo.util.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.limite_certo.entity.BaseEntity;
import com.limite_certo.util.converter.ConvertibleToEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

@Setter
public abstract class BaseDTO<T extends BaseEntity<?>> implements ConvertibleToEntity<T> {
    @Schema(hidden = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Long id = null;
    public abstract Long getId();
}
