package com.limite_certo.application.service;

import com.limite_certo.infrastructure.persistence.entity.ClienteEntity;
import com.limite_certo.infrastructure.persistence.repository.ClienteEntityRepository;
import com.limite_certo.infrastructure.util.dto.ClienteBaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseService<ClienteEntity, ClienteBaseDTO> {
    private final ClienteEntityRepository repository;

    @Autowired
    protected ClienteService(ClienteEntityRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected ClienteEntity convertToEntity(ClienteBaseDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected ClienteBaseDTO convertToEntity(ClienteEntity entity) {
        return entity.toDTO();
    }
}
