package com.example.trabalhofinal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private int diasExtras;
    private double desconto;
    private LocalDate validade;
    private boolean ativa;


    public Promocao() {}

    public Promocao(String descricao, int diasExtras, double desconto, LocalDate validade, boolean ativa) {
        this.descricao = descricao;
        this.diasExtras = diasExtras;
        this.desconto = desconto;
        this.validade = validade;
        this.ativa = ativa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDiasExtras() {
        return diasExtras;
    }

    public void setDiasExtras(int diasExtras) {
        this.diasExtras = diasExtras;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa =ativa;
    }
}
