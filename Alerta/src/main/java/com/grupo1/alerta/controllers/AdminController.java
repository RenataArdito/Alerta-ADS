package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Processo;
import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.models.Historico;
import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.SolicitacaoService;
import com.grupo1.alerta.services.ProcessoService;
import com.grupo1.alerta.services.HistoricoService;
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
    
    @Autowired
    private ProcessoService processoService;
    
    @Autowired
    private HistoricoService historicoService;

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
            // Cria o novo processo com os dados do admin
            Processo processo = new Processo();
            processo.setStatus(status);
            processo.setDetalhes(detalhes);
            processo.setAdmin(usuario);
            processo.setSolicitacao(s);
            processoService.salvarProcesso(processo);
            
            // Cria o registro no histórico com todas as informações mescladas
            Historico historico = new Historico(s, status, detalhes, usuario);
            historicoService.salvarHistorico(historico);
        }
        return "redirect:/admin";
    }
}