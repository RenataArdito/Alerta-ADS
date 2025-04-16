package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Historico;
import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.HistoricoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/historico")
    public String historico(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/login";
        
        List<Historico> historicos = usuario.getRole() ? 
                                     historicoService.listarHistorico() : 
                                     historicoService.listarPorUsuario(usuario);
        model.addAttribute("historicos", historicos);
        model.addAttribute("usuarioLogado", usuario);
        return "historico";
    }
}