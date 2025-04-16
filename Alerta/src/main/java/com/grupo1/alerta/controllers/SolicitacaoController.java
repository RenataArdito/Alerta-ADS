package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.models.Processo;
import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.services.SolicitacaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    // Exibe o formulário de criação de solicitação (não para admin)
    @GetMapping("/solicitacao")
    public String solicitacao(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/login";
        // Se for admin, redireciona para home (ou outra rota desejada)
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

    // Permite edição se for usuário comum e
    // se a solicitação ainda não foi processada OU se o processo tem status "EM AVALIAÇÃO".
    @GetMapping("/solicitacao/editar/{id}")
    public String editarSolicitacao(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null || usuario.getRole()) return "redirect:/login"; // Apenas usuários comuns podem editar
        
        Solicitacao solicitacao = solicitacaoService.buscarPorId(id);
        if (solicitacao == null || !solicitacao.getUsuario().getId().equals(usuario.getId())) {
            return "redirect:/historico";
        }
        
        Processo processo = solicitacao.getProcesso();
        // Se já houver processo E o status não for "EM AVALIAÇÃO", impede edição
        if (processo != null && !processo.getStatus().equalsIgnoreCase("EM AVALIAÇÃO")) {
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
        if (solicitacao != null) {
            // Se já houver processo, só permite se o status for "EM AVALIAÇÃO"
            if (solicitacao.getProcesso() != null && 
                !solicitacao.getProcesso().getStatus().equalsIgnoreCase("EM AVALIAÇÃO")) {
                return "redirect:/historico";
            }
            solicitacao.setCategoria(categoria);
            solicitacao.setEndereco(endereco);
            solicitacao.setDescricao(descricao);
            solicitacaoService.salvarSolicitacao(solicitacao);
        }
        return "redirect:/historico";
    }
}