package com.grupo1.alerta.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private String endereco;
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")  // corresponde ao seu DDL
    private Usuario usuario;
    
    @OneToOne(mappedBy = "solicitacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Processo processo;

    public Solicitacao() {}

    public Solicitacao(String categoria, String endereco, String descricao, Usuario usuario) {
        this.categoria = categoria;
        this.endereco = endereco;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Processo getProcesso() {
        return processo;
    }
    public void setProcesso(Processo processo) {
        this.processo = processo;
        if (processo != null) {
            processo.setSolicitacao(this);
        }
    }
}