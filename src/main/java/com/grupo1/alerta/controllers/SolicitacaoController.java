package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping("/solicitacao")
    public String solicitacao() {
        return "solicitacao";
    }

    @PostMapping("/solicitacao")
    public String processoSolicitacao(@RequestParam String categoria,
                                      @RequestParam String endereco,
                                      @RequestParam String descricao) {
        Solicitacao solicitacao = new Solicitacao(categoria, endereco, descricao);
        solicitacaoService.salvarSolicitacao(solicitacao);
        return "redirect:/historico";
    }
}
