package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    @PostMapping("/perfil/editar")
    public String atualizarPerfil(@RequestParam String nome,
                                  @RequestParam String email,
                                  @RequestParam String endereco,
                                  @RequestParam String nascimento,
                                  @RequestParam String senha,
                                  HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataNascimento = sdf.parse(nascimento);
            usuario.setNascimento(dataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:/perfil?erro=data";
        }

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setEndereco(endereco);
        usuario.setSenha(senha);

        // Salva no banco
        usuarioService.cadastrarUsuario(usuario);

        // Atualiza 
        session.setAttribute("usuarioLogado", usuario);

        return "redirect:/perfil";
    }
}
