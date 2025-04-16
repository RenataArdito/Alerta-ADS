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

    // Exibe o formulário de criação de solicitação (não para admin)
    @GetMapping("/solicitacao")
    public String solicitacao(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/login";
        if (usuario.getRole()) return "redirect:/home";
        return "solicitacao";
    }

    // Salva nova solicitação
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

    // Métodos de edição permanecem inalterados conforme a regra de negócio
    @GetMapping("/solicitacao/editar/{id}")
    public String editarSolicitacao(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null || usuario.getRole()) return "redirect:/login"; // somente usuário comum pode editar
        Solicitacao solicitacao = solicitacaoService.buscarPorId(id);
        if (solicitacao == null || !solicitacao.getUsuario().getId().equals(usuario.getId())) {
            return "redirect:/historico";
        }
        // Supondo que somente solicitações sem processamento possam ser editadas
        if (solicitacao.getProcesso() != null) {
            return "redirect:/historico";
        }
        model.addAttribute("solicitacao", solicitacao);
        return "solicitacao_edit";
    }

    @PostMapping("/solicitacao/editar")
    public String atualizarSolicitacao(@RequestParam Long id,
                                       @RequestParam String categoria,
                                       @RequestParam String endereco,
                                       @RequestParam String descricao,
                                       HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null || usuario.getRole()) return "redirect:/login";
        Solicitacao solicitacao = solicitacaoService.buscarPorId(id);
        if (solicitacao != null && solicitacao.getProcesso() == null) {
            solicitacao.setCategoria(categoria);
            solicitacao.setEndereco(endereco);
            solicitacao.setDescricao(descricao);
            solicitacaoService.salvarSolicitacao(solicitacao);
        }
        return "redirect:/historico";
    }
}