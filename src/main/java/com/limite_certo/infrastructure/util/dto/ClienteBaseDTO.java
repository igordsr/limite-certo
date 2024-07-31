package com.limite_certo.infrastructure.util.dto;

import com.limite_certo.infrastructure.persistence.entity.ClienteEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteBaseDTO extends BaseDTO<ClienteEntity> {
    String cpf;
    String nome;
    String email;
    String telefone;
    String rua;
    String cidade;
    String estado;
    String cep;
    String pais;

    @Override
    public ClienteEntity toEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(cpf);
        clienteEntity.setNome(nome);
        clienteEntity.setEmail(email);
        clienteEntity.setTelefone(telefone);
        clienteEntity.setRua(rua);
        clienteEntity.setCidade(cidade);
        clienteEntity.setEstado(estado);
        clienteEntity.setCep(cep);
        clienteEntity.setPais(pais);
        return clienteEntity;
    }
}