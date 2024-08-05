package com.limite_certo.repository;

import com.limite_certo.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    UserLogin findByUsernameIgnoreCase(String username);


}