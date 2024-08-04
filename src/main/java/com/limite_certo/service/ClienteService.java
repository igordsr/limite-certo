package com.limite_certo.service;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.entity.ClienteEntity;
import com.limite_certo.repository.ClienteEntityRepository;
import com.limite_certo.util.dto.ClienteBaseDTO;
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

    @Override
    protected void executarValidacoesAntesDeCadastrar(ClienteBaseDTO dto) throws RuntimeException {
        this.repository.findByCpf(dto.getCpf()).ifPresent(cliente -> {
            throw new CustomException("Usuário com CPF " + cliente.getCpf() + " já existe.");
        });
    }

    public ClienteEntity findByCpf(final String cpf) {
        return this.repository.findByCpf(cpf)
                .orElseThrow(() -> new CustomException("Usuário com CPF " + cpf + " não existe."));

    }
}
