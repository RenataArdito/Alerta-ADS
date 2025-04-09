package com.grupo1.alerta.controllers;

import com.grupo1.alerta.models.Historico;
import com.grupo1.alerta.services.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/Historico")
    public String historico(Model model) {
        List<Historico> historicos = historicoService.listarHistorico();
        model.addAttribute("historicos", historicos);
        return "Historico";
    }
}