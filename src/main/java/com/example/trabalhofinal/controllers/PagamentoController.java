package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.models.Pagamento;
import com.example.trabalhofinal.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servcad")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/pagamentos")
    public List<Pagamento> getAllPagamentos() {
        return pagamentoService.getAllPagamentos();
    }

    @PostMapping("/pagamentos")
    public Pagamento savePagamento(@RequestBody Pagamento pagamento) {
        return pagamentoService.savePagamento(pagamento);
    }

    @PutMapping("pagamentos/{id}")
    public Pagamento updatePagamento(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        pagamento.setId(id);
        return pagamentoService.updatePagamento(pagamento);
    }

    @DeleteMapping("pagamentos/{id}")
    public void deletePagamento(@PathVariable Long id) {
        pagamentoService.deletePagamento(id);
    }

    @PostMapping("pagamentos/notificacao")
    public void receberNotificacaoPagamento(@RequestBody Pagamento pagamento) {
        pagamentoService.processarNotificacaoPagamento(pagamento);
    }
}
