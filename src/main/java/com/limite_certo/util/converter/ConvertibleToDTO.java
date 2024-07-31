package com.limite_certo.util.converter;

import com.limite_certo.util.dto.BaseDTO;

public interface ConvertibleToDTO<T extends BaseDTO<?>> {
    T toDTO();
}
