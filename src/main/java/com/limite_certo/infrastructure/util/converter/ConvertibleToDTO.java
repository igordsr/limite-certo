package com.limite_certo.infrastructure.util.converter;

import com.limite_certo.infrastructure.util.dto.BaseDTO;

public interface ConvertibleToDTO<T extends BaseDTO> {
    T toDTO();
}
