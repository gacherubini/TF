package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.services.AplicativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aplicativos")
public class AplicativoController {

    @Autowired
    private AplicativoService aplicativoService;

    @GetMapping
    public List<Aplicativo> getAllAplicativos() {
        return aplicativoService.getAllAplicativos();
    }

    @PostMapping
    public Aplicativo saveAplicativo(@RequestBody Aplicativo aplicativo) {
        return aplicativoService.saveAplicativo(aplicativo);
    }

    @PutMapping("/{id}")
    public Aplicativo updateAplicativo(@PathVariable Long id, @RequestBody Aplicativo aplicativo) {
        aplicativo.setId(id);
        return aplicativoService.updateAplicativo(aplicativo);
    }

    @DeleteMapping("/{id}")
    public void deleteAplicativo(@PathVariable Long id) {
        aplicativoService.deleteAplicativo(id);
    }
}
