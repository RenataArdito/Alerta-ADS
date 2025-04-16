package com.grupo1.alerta.repositories;

import com.grupo1.alerta.models.Historico;
import com.grupo1.alerta.models.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    Historico findBySolicitacao(Solicitacao solicitacao);
}