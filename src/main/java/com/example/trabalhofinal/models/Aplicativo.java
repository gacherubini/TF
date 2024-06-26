package com.example.trabalhofinal.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Aplicativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double custoMensal;

    @OneToMany(mappedBy = "aplicativo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assinatura> assinaturas = new ArrayList<>();

    public Aplicativo() {
    }

    public Aplicativo(String nome, double custoMensal) {
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCustoMensal() {
        return custoMensal;
    }

    public void setCustoMensal(double custoMensal) {
        this.custoMensal = custoMensal;
    }


}
