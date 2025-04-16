package com.grupo1.alerta.services;

import com.grupo1.alerta.models.Historico;
import com.grupo1.alerta.models.Usuario;
import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.repositories.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    public List<Historico> listarHistorico() {
        return historicoRepository.findAll();
    }
    
    // Filtra histórico para usuários comuns (com base no id do usuário da solicitação)
    public List<Historico> listarPorUsuario(Usuario usuarioLogado) {
        return historicoRepository.findAll()
          .stream()
          .filter(h -> {
              Solicitacao s = h.getSolicitacao();
              return s != null 
                  && s.getUsuario() != null
                  && s.getUsuario().getId().equals(usuarioLogado.getId());
          })
          .collect(Collectors.toList());
    }
    
    public void salvarHistorico(Historico historico) {
        historicoRepository.save(historico);
    }
    
    // Novo método para buscar histórico por solicitação
    public Historico buscarPorSolicitacao(Solicitacao solicitacao) {
        return historicoRepository.findBySolicitacao(solicitacao);
    }
}