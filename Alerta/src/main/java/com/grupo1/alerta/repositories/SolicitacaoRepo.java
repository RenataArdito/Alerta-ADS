package com.grupo1.alerta.repositories;

import com.grupo1.alerta.models.Solicitacao;
import com.grupo1.alerta.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitacaoRepo extends JpaRepository<Solicitacao, Long> {
    List<Solicitacao> findByUsuario(Usuario usuario);
}