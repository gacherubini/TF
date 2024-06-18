package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.services.AssinaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/servcad")
public class AssinaturaController {

    @Autowired
    private AssinaturaService assinaturaService;

    @GetMapping("/assinaturas")
    public List<Assinatura> getAllAssinaturas() {
        return assinaturaService.getAllAssinaturas();
    }

    @GetMapping("/assinaturas/cliente/{clienteId}")
    public List<Assinatura> getAssinaturasByClienteId(@PathVariable Long clienteId) {
        return assinaturaService.getAssinaturasByClienteId(clienteId);
    }

    @GetMapping("/assinaturas/aplicativo/{aplicativoId}")
    public List<Assinatura> getAssinaturasByAplicativoId(@PathVariable Long aplicativoId) {
        return assinaturaService.getAssinaturasByAplicativoId(aplicativoId);
    }

    @PostMapping("/assinaturas")
    public Assinatura saveAssinatura(@RequestBody Assinatura assinatura) {
        return assinaturaService.saveAssinatura(assinatura);
    }

    @PutMapping("/assinaturas/{id}")
    public Assinatura updateAssinatura(@PathVariable Long id, @RequestBody Assinatura assinatura) {
        assinatura.setId(id);
        return assinaturaService.updateAssinatura(assinatura);
    }

    @DeleteMapping("/assinaturas/{id}")
    public void deleteAssinatura(@PathVariable Long id) {
        assinaturaService.deleteAssinatura(id);
    }

    @PutMapping("/assinaturas/vigencia/{id}")
    public void updateAssinaturaVigencia(@PathVariable Long id, @RequestBody VigenciaRequest vigenciaRequest) {
        assinaturaService.updateAssinaturaVigencia(id, vigenciaRequest.getInicioVigencia(), vigenciaRequest.getFimVigencia());
    }

    @GetMapping("/valida")
    public boolean isAssinaturaValida(@RequestParam Long clienteId, @RequestParam Long assinaturaId) {
        return assinaturaService.isAssinaturaValida(clienteId, assinaturaId);
    }
}

class VigenciaRequest {
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;

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
}
