package com.example.trabalhofinal.dto;

public class PagamentoRespostaDTO {
    private String status;
    private String data;
    private double valorEstornado;

    // Getters e Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValorEstornado() {
        return valorEstornado;
    }

    public void setValorEstornado(double valorEstornado) {
        this.valorEstornado = valorEstornado;
    }
}
