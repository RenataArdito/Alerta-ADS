package com.grupo1.alerta.repositories;

import com.grupo1.alerta.models.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
}
