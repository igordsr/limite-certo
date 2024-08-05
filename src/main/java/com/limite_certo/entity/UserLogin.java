package com.limite_certo.entity;

import com.limite_certo.dto.BaseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Setter
@Getter
@Entity
@DynamicUpdate
@Table(name = "tb_login")
public class UserLogin extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    @Override
    public BaseDTO<?> toDTO() {
        return null;
    }
}
