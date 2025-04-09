package com.grupo1.alerta.services;

import com.grupo1.alerta.models.Login;
import com.grupo1.alerta.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean autenticar(String email, String senha) {
        Login login = loginRepository.findByEmailAndSenha(email, senha);
        return login != null;
    }
}