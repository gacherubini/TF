package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.dto.AssinaturaDTO;
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

    @PostMapping("/assinaturas")
    public Assinatura saveAssinatura(@RequestBody Assinatura assinatura) {
        return assinaturaService.saveAssinatura(assinatura);
    }

    @PostMapping
    public Assinatura saveAssinaturaWithCliAndAppIDs(@RequestBody AssinaturaDTO assinaturaDTO) {
        return assinaturaService.saveAssinaturaWithCliAndAppIDs(assinaturaDTO.getClienteId(), assinaturaDTO.getAplicativoId());
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

    @GetMapping("assinaturas/valida")
    public boolean isAssinaturaValidaPar(@RequestParam Long clienteId, @RequestParam Long assinaturaId) {
        return assinaturaService.isAssinaturaValida(clienteId, assinaturaId);
    }

    @GetMapping("assinatutas/assinvalida/{codassin}")
    public boolean isAssinaturaValida(@PathVariable Long codassin) {
        return assinaturaService.isAssinaturaValida(codassin);
    }

    @GetMapping("assinaturas/assinapp/{codapp}")
    public List<AssinaturaDTO> getAssinaturasPorAplicativo(@PathVariable Long codapp) {
        return assinaturaService.getAssinaturasPorAplicativo(codapp);
    }

    @GetMapping("assinaturas/{tipo}")
    public List<AssinaturaDTO> getAssinaturasPorTipo(@PathVariable String tipo) {
        return assinaturaService.getAssinaturasPorTipo(tipo);
    }

    @GetMapping("assinaturas/assincli/{codcli}")
    public List<AssinaturaDTO> getAssinaturasPorCliente(@PathVariable Long codcli) {
        return assinaturaService.getAssinaturasPorCliente(codcli);
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
