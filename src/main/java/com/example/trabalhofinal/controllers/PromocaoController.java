package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.models.Promocao;
import com.example.trabalhofinal.services.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servcad")
public class PromocaoController {

    @Autowired
    private PromocaoService promocaoService;

    @GetMapping("promocoes")
    public List<Promocao> getAllPromocoes() {
        return promocaoService.getAllPromocoes();
    }

    @GetMapping("promocoes/{id}")
    public Promocao getPromocaoById(@PathVariable Long id) {
        return promocaoService.getPromocaoById(id);
    }

    @PostMapping("promocoes")
    public Promocao createPromocao(@RequestBody Promocao promocao) {
        return promocaoService.createPromocao(promocao);
    }

    @PutMapping("promocoes/{id}")
    public Promocao updatePromocao(@PathVariable Long id, @RequestBody Promocao promocao) {
        return promocaoService.updatePromocao(id, promocao);
    }

    @DeleteMapping("promocoes/{id}")
    public void deletePromocao(@PathVariable Long id) {
        promocaoService.deletePromocao(id);
    }
}