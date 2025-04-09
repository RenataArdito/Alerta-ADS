package com.grupo1.alerta.repositories;

import com.grupo1.alerta.models.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}