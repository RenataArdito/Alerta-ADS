package com.grupo1.alerta.repositories;

import com.grupo1.alerta.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByEmailAndSenha(String email, String senha);
}