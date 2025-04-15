package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.SolicitacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping("/admin")
    public String paginaAdmin(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario != null && usuario.getRole()) {
            List<Solicitacao> solicitacoes = solicitacaoService.listarSolicitacoes();
            model.addAttribute("solicitacoes", solicitacoes);
            return "admin";
        }
        return "redirect:/login";
    }

    @PostMapping("/admin/update")
    public String atualizarSolicitacao(@RequestParam Long solicitacaoId,
                                       @RequestParam String status,
                                       @RequestParam(required = false) String detalhes,
                                       HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null || !usuario.getRole()) return "redirect:/login";

        Solicitacao s = solicitacaoService.buscarPorId(solicitacaoId);
        if (s != null) {
            s.setStatus(status);
            s.setDetalhes(detalhes);
            solicitacaoService.salvarSolicitacao(s);
        }
        return "redirect:/admin";
    }
}