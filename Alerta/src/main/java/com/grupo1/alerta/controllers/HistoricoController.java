package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.SolicitacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoricoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping("/historico")
    public String historico(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/login";

        List<Solicitacao> solicitacoes = usuario.getRole() ?
            solicitacaoService.listarSolicitacoes() :
            solicitacaoService.listarPorUsuario(usuario);

        model.addAttribute("solicitacoes", solicitacoes);
        return "historico";
    }
}
