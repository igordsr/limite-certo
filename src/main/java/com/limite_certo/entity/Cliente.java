package com.limite_certo.entity;

import com.limite_certo.dto.ClienteBaseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tb_clientes")
public class Cliente extends BaseEntity<ClienteBaseDTO> {
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String pais;

    @OneToMany(mappedBy = "cliente")
    private Set<Cartao> cartoes;

    @OneToMany(mappedBy = "cliente")
    private Set<Pagamento> pagamentos;

    @Override
    public ClienteBaseDTO toDTO() {
        final ClienteBaseDTO clienteBaseDTO = new ClienteBaseDTO();
        clienteBaseDTO.setId(id);
        clienteBaseDTO.setCpf(cpf);
        clienteBaseDTO.setNome(nome);
        clienteBaseDTO.setEmail(email);
        clienteBaseDTO.setTelefone(telefone);
        clienteBaseDTO.setRua(rua);
        clienteBaseDTO.setCidade(cidade);
        clienteBaseDTO.setEstado(estado);
        clienteBaseDTO.setCep(cep);
        clienteBaseDTO.setPais(pais);
        return clienteBaseDTO;
    }
}
