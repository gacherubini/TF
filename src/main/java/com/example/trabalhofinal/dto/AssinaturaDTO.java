package com.example.trabalhofinal.dto;

import java.time.LocalDate;

public class AssinaturaDTO {
    private Long id;
    private Long clienteId;
    private Long aplicativoId;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
    private String status;

    // Construtor
    public AssinaturaDTO(Long id, Long clienteId, Long aplicativoId, LocalDate inicioVigencia, LocalDate fimVigencia, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.aplicativoId = aplicativoId;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getAplicativoId() {
        return aplicativoId;
    }

    public void setAplicativoId(Long aplicativoId) {
        this.aplicativoId = aplicativoId;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public LocalDate getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
