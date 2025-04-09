package com.grupo1.alerta.repositories;

import com.grupo1.alerta.models.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepo extends JpaRepository<Solicitacao, Long> {
}