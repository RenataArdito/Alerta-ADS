package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.SolicitacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping("/solicitacao")
    public String solicitacao(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/login";
        if (usuario.getRole()) return "redirect:/home"; // Admin não acessa
        return "solicitacao";
    }

    @PostMapping("/solicitacao")
    public String processoSolicitacao(@RequestParam String categoria,
                                      @RequestParam String endereco,
                                      @RequestParam String descricao,
                                      HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario != null && !usuario.getRole()) {
            Solicitacao solicitacao = new Solicitacao(categoria, endereco, descricao, usuario);
            solicitacaoService.salvarSolicitacao(solicitacao);
            return "redirect:/historico";
        }
        return "redirect:/login";
    }
}
