package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginRedirect(HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            return "redirect:/perfil";
        }
        return "login"; 
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam String usuario,
                                    @RequestParam String senha,
                                    HttpSession session,
                                    Model model) {
        Usuario user = usuarioService.autenticar(usuario, senha);

        if (user != null) {
            session.setAttribute("usuarioLogado", user);
            return "redirect:/perfil";
        } else {
            model.addAttribute("mensagemErro", "Usuário não encontrado!");
            return "redirect:/cadastro"; 
        }
    }
}