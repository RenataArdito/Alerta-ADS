package com.grupo1.alerta.models;

import jakarta.persistence.*;

@Entity
@Table(name = "historico")
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Solicitacao solicitacao;

    public Historico() {}

    public Historico(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}