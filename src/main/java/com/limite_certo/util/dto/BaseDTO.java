package com.limite_certo.util.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.limite_certo.entity.BaseEntity;
import com.limite_certo.util.converter.ConvertibleToEntity;
import com.limite_certo.util.view.Views;
import lombok.Setter;

@Setter
public abstract class BaseDTO<T extends BaseEntity<?>> implements ConvertibleToEntity<T> {

    @JsonView(Views.IdView.class)
    protected Long id = null;

    public abstract Long getId();
}
