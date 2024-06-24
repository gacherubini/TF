package com.example.trabalhofinal.dto;

    public class RegistrarPagamentoDTO {
    private int dia;
    private int mes;
    private int ano;
    private Long codass;
    private double valorPago;
    private Long codPromocao;


        // Getters e Setters
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Long getCodass() {
        return codass;
    }

    public void setCodass(Long codass) {
        this.codass = codass;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Long getCodPromocao() {return codPromocao;}

        public void setCodPromocao(Long codPromocao) {this.codPromocao = codPromocao;}
}
