package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String formCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@RequestParam String email,
                                   @RequestParam String senha) {
        Usuario novo = new Usuario(email, senha, "USER");
        usuarioService.cadastrarUsuario(novo);
        return "redirect:/Login";
    }
}