package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.dto.AtualizarCustoDTO;
import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.services.AplicativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servcad")
public class AplicativoController {

    @Autowired
    private AplicativoService aplicativoService;

    @GetMapping("/aplicativos")
    public List<Aplicativo> getAllAplicativos() {
        return aplicativoService.getAllAplicativos();
    }

    @PostMapping("/aplicativos")
    public Aplicativo saveAplicativo(@RequestBody Aplicativo aplicativo) {
        return aplicativoService.saveAplicativo(aplicativo);
    }

    @PutMapping("aplicativos/{id}")
    public Aplicativo updateAplicativo(@PathVariable Long id, @RequestBody Aplicativo aplicativo) {
        aplicativo.setId(id);
        return aplicativoService.updateAplicativo(aplicativo);
    }

    @DeleteMapping("aplicativos/{id}")
    public void deleteAplicativo(@PathVariable Long id) {
        aplicativoService.deleteAplicativo(id);
    }

    @PostMapping("/atualizacusto/{idAplicativo}")
    public Aplicativo atualizarCustoMensal(@PathVariable Long idAplicativo, @RequestBody AtualizarCustoDTO atualizarCustoDTO) {
        return aplicativoService.atualizarCustoMensal(idAplicativo, atualizarCustoDTO.getCusto());
    }
}
