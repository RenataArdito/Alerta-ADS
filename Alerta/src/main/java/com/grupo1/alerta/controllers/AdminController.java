package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/admin")
    public String paginaAdmin(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null && usuario.isRole()) { // true = admin
            return "admin";
        }
        return "redirect:/login";
    }
}