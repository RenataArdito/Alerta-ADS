package com.grupo1.alerta.models;

import jakarta.persistence.*;

@Entity
@Table(name = "processos")
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;       // Exemplo: "EM AVALIAÇÃO", "FINALIZADO", "NEGADO"
    private String detalhes;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Usuario admin; // Administrador que respondeu

    @OneToOne
    @JoinColumn(name = "id_solicitacao", unique = true)
    private Solicitacao solicitacao;

    public Processo() {}

    // Getters e setters

    public Long getId() {
        return id;
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

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }
    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}
