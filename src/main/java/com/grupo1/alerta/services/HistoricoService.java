package com.grupo1.alerta.services;

import com.grupo1.alerta.models.Historico;
import com.grupo1.alerta.repositories.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    public List<Historico> listarHistorico() {
        return historicoRepository.findAll();
    }
}
