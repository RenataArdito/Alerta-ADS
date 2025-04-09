package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Login;
import com.grupo1.alerta.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/Login")
    public String login() {
        return "Login";
    }

    @PostMapping("/Login")
    public String processoLogin(@RequestParam String usuario,
                                 @RequestParam String senha) {
        Login login = loginRepository.findByEmailAndSenha(usuario, senha);
        if (login != null) {
            return "redirect:/";
        }
        return "Login"; // retorna para login se falhar
    }
}
