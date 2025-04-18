package com.grupo1.alerta.models;

import jakarta.persistence.*;

@Entity
@Table(name = "historico")
public class Historico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referência à solicitação original
    @OneToOne
    @JoinColumn(name = "id_solicitacao", unique = true)
    private Solicitacao solicitacao;

    // Dados preenchidos pelo admin
    private String status;
    private String detalhes;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Usuario admin;

    public Historico() {}

    public Historico(Solicitacao solicitacao, String status, String detalhes, Usuario admin) {
        this.solicitacao = solicitacao;
        this.status = status;
        this.detalhes = detalhes;
        this.admin = admin;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }
    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetalhes() {
        return detalhes;
    }
    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Usuario getAdmin() {
        return admin;
    }
    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }
}