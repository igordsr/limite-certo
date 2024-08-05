package com.limite_certo.service;

import com.limite_certo.controller.exception.modal.CustomException;
import com.limite_certo.entity.Cliente;
import com.limite_certo.repository.ClienteRepository;
import com.limite_certo.dto.ClienteBaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService extends BaseService<Cliente, ClienteBaseDTO> {
    private final ClienteRepository repository;

    @Autowired
    protected ClienteService(ClienteRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected Cliente convertToEntity(ClienteBaseDTO dto) {
        return dto.toEntity();
    }

    @Override
    protected ClienteBaseDTO convertToEntity(Cliente entity) {
        return entity.toDTO();
    }

    @Override
    protected void executarValidacoesAntesDeCadastrar(ClienteBaseDTO dto) throws RuntimeException {
        Optional<Cliente> clienteOptional = this.repository.findByCpf(dto.getCpf());
        if (clienteOptional.isPresent()) {
            final Cliente cliente = clienteOptional.get();
            throw new CustomException("Usuário com CPF " + cliente.getCpf() + " já existe.");
        }
    }

    public Cliente findByCpf(final String cpf) {
        return this.repository.findByCpf(cpf).orElseThrow(() -> new CustomException("Usuário com CPF " + cpf + " não existe."));
    }
}
