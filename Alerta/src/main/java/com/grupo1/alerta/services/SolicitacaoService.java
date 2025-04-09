package com.grupo1.alerta.services;

import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.repositories.SolicitacaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepo solicitacaoRepo;

    public void salvarSolicitacao(Solicitacao solicitacao) {
        solicitacaoRepo.save(solicitacao);
    }

    public List<Solicitacao> listarSolicitacoes() {
        return solicitacaoRepo.findAll();
    }

    public void excluirSolicitacao(Long id) {
        solicitacaoRepo.deleteById(id);
    }
}