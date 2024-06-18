package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.services.AssinaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController {

    @Autowired
    private AssinaturaService assinaturaService;

    @GetMapping
    public List<Assinatura> getAllAssinaturas() {
        return assinaturaService.getAllAssinaturas();
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Assinatura> getAssinaturasByClienteId(@PathVariable Long clienteId) {
        return assinaturaService.getAssinaturasByClienteId(clienteId);
    }

    @GetMapping("/aplicativo/{aplicativoId}")
    public List<Assinatura> getAssinaturasByAplicativoId(@PathVariable Long aplicativoId) {
        return assinaturaService.getAssinaturasByAplicativoId(aplicativoId);
    }

    @PostMapping
    public Assinatura saveAssinatura(@RequestBody Assinatura assinatura) {
        return assinaturaService.saveAssinatura(assinatura);
    }

    @PutMapping("/{id}")
    public Assinatura updateAssinatura(@PathVariable Long id, @RequestBody Assinatura assinatura) {
        assinatura.setId(id);
        return assinaturaService.updateAssinatura(assinatura);
    }

    @DeleteMapping("/{id}")
    public void deleteAssinatura(@PathVariable Long id) {
        assinaturaService.deleteAssinatura(id);
    }

    @PutMapping("/validade/{id}")
    public void updateAssinaturaValidade(@PathVariable Long id, @RequestBody String validade) {
        assinaturaService.updateAssinaturaValidade(id, LocalDate.parse(validade));
    }
}
