package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Historico;
import com.grupo1.alerta.models.Processo;
import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.HistoricoService;
import com.grupo1.alerta.services.ProcessoService;
import com.grupo1.alerta.services.SolicitacaoService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("usuarioLogado", usuario);
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
            
            // Vincula o processo à solicitação e salva a alteração
            s.setProcesso(processo);
            solicitacaoService.salvarSolicitacao(s);
            
            // Verifica se já existe um histórico para essa solicitação
            Historico historico = historicoService.buscarPorSolicitacao(s);
            if (historico == null) {
                historico = new Historico(s, status, detalhes, usuario);
            } else {
                historico.setStatus(status);
                historico.setDetalhes(detalhes);
                historico.setAdmin(usuario);
            }
            historicoService.salvarHistorico(historico);
        }
        return "redirect:/historico";
    }
}